import javaExample.Publisher

// Data class for library members
data class Member(
    val id: String,
    val name: String
)

// Step 1: create relevant data class for books, including reference to Java Publisher class
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val publisher: Publisher?,
    val borrowed: Boolean = false
)

// Sealed class for loan results
sealed class LoanResult {
    data class Success(val book: Book) : LoanResult()
    data class Error(val message: String) : LoanResult()
}

// Main library class demonstrating various Kotlin features
class Library {
    // members already initialized
    val members = mutableListOf<Member>()

    // Step 2: Initialize books
    val books = mutableListOf<Book>()

    // Step 3: Function to add a book
    fun addBook(book: Book) = books.add(book)

    // Step 4: Find a book in the library with nullable return
    fun findBook(id: String): Book? = books.find { it.id == id }

    // Step 5: allow members to borrow books
    fun borrowBook(memberId: String, bookId: String): LoanResult {
        val member = members.find { it.id == memberId }
        val book = findBook(bookId)

        return when {
            member == null -> LoanResult.Error("Member not found")
            book == null -> LoanResult.Error("Book not found")
            book.borrowed -> LoanResult.Error("Book is already borrowed")
            else -> {
                // Immutable book with updated borrowed status
                val updatedBook = book.copy(borrowed = true)

                // Demonstration of list operations and immutability
                books.remove(book)
                books.add(updatedBook)

                LoanResult.Success(updatedBook)
            }
        }
    }
}

// Interactive tutorial demonstration
fun main() {
    // Initial library with one member
    val library = Library()
    library.members.add(Member("M1", "Jane Smith"))

    // Step 1: Adding a publisher using the Java publisher class and create books
    val publisher = Publisher("HarperCollins")

    val initialBooks = listOf(
        Book("1", "1984", "George Orwell", publisher),
        Book("2", "Brave New World", "Aldous Huxley", publisher),
    )

    println("Printing from a collection of data classes: ")
    println(initialBooks)

    // Step 2: add books to the library
    initialBooks.forEach { library.addBook(it) }

    // Step 3: find the book
    println("\nTrying to find a book:")
    val book = library.findBook("1")
    book?.let { println("Found book: ${it.title}") } ?: println("Book not found")

    // Demonstrate sealed class and pattern matching
    println("\nTrying to borrow a book:")
    when (val result = library.borrowBook("M1", "1")) {
        is LoanResult.Success -> println("Successfully borrowed: ${result.book.title}")
        is LoanResult.Error -> println("Failed to borrow: ${result.message}")
        else -> {}
    }

}