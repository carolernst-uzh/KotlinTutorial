import javaExample.Publisher

// Data class for library members
data class Member(
    val id: String,
    val name: String
)

// Step 1: create relevant data class for books, including reference to Java Publisher class
// TODO

// Sealed class for loan results
//sealed class LoanResult {
//    data class Success(val book: Book) : LoanResult()
//    data class Error(val message: String) : LoanResult()
//}

// Main library class demonstrating various Kotlin features
class Library {
    // members already initialized
    val members = mutableListOf<Member>()

    // Step 2: Initialize books and create a function to add books
    // TODO

    // Step 3: Find a book in the library with nullable return
    // TODO

    // Step 4: allow members to borrow books
    // TODO
}

// Test the code we have written
fun main() {
    // Initial library with one member
    val library = Library()
    library.members.add(Member("M1", "Jane Smith"))

    // Step 1: Adding a publisher using the Java publisher class and create books
//    val publisher = Publisher("HarperCollins")
//
//    val initialBooks = listOf(
//        Book("1", "1984", "George Orwell", publisher),
//        Book("2", "Brave New World", "Aldous Huxley", publisher),
//    )
//
//    println("Printing from a collection of data classes: ")
//    println(initialBooks)

    // Step 2: add books to the library
    // TODO

    // Step 3: find the book
//    println("\nTrying to find a book:")
//    val book = library.findBook("1")
//    book?.let { println("Found book: ${it.title}") } ?: println("Book not found")

    // Step 4: borrow the book
//    println("\nTrying to borrow a book:")
//    when (val result = library.borrowBook("M1", "1")) {
//        is LoanResult.Success -> println("Successfully borrowed: ${result.book.title}")
//        is LoanResult.Error -> println("Failed to borrow: ${result.message}")
//        else -> {}
//    }

}