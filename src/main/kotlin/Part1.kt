// Carol to work out in more detail


// Data classes for our domain
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val borrowed: Boolean = false
)

data class Member(
    val id: String,
    val name: String,
    val membershipType: MembershipType,
    var borrowedBooks: List<Book> = listOf()
)

enum class MembershipType {
    STANDARD, PREMIUM
}

// Sealed class for loan results
sealed class LoanResult {
    data class Success(val book: Book) : LoanResult()
    data class Error(val message: String) : LoanResult()
}

// Main library class demonstrating various Kotlin features
class Library {
    private val books = mutableListOf<Book>()
    private val members = mutableListOf<Member>()

    // Extension function demonstration
    fun Member.canBorrowBooks(): Boolean = when (membershipType) {
        MembershipType.STANDARD -> borrowedBooks.size < 3
        MembershipType.PREMIUM -> borrowedBooks.size < 5
    }

    // Function to add a book
    fun addBook(book: Book) {
        books.add(book)
    }

    // Nullable return type and safe calls demonstration
    fun findBook(id: String): Book? = books.find { it.id == id }

    // Smart casts and null safety demonstration
    fun borrowBook(memberId: String, bookId: String): LoanResult {
        val member = members.find { it.id == memberId }
        val book = findBook(bookId)

        return when {
            member == null -> LoanResult.Error("Member not found")
            book == null -> LoanResult.Error("Book not found")
            book.borrowed -> LoanResult.Error("Book is already borrowed")
            !member.canBorrowBooks() -> LoanResult.Error("Member has reached their borrowing limit")
            else -> {
                // Immutable book with updated borrowed status
                val updatedBook = book.copy(borrowed = true)

                // Demonstration of list operations and immutability
                books.remove(book)
                books.add(updatedBook)

                // Update member's borrowed books
                member.borrowedBooks = member.borrowedBooks + updatedBook

                LoanResult.Success(updatedBook)
            }
        }
    }

    // Higher-order function demonstration
    fun filterBooks(predicate: (Book) -> Boolean): List<Book> = books.filter(predicate)

    // Function with default parameters
    fun addMember(
        id: String,
        name: String,
        membershipType: MembershipType = MembershipType.STANDARD
    ) {
        members.add(Member(id, name, membershipType))
    }

    // Demonstration of collection operations
    fun getBookStatistics(): Map<String, Any> {
        return mapOf(
            "totalBooks" to books.size,
            "availableBooks" to books.count { !it.borrowed },
            "borrowedBooks" to books.count { it.borrowed },
            "booksByYear" to books.groupBy { it.year },
            "authorStats" to books.groupBy { it.author }
                .mapValues { it.value.size }
        )
    }
}

// Interactive tutorial demonstration
fun main() {
    val library = Library()

    // Adding books using named parameters
    val initialBooks = listOf(
        Book("1", "1984", "George Orwell", 1949),
        Book("2", "Brave New World", "Aldous Huxley", 1932),
        Book("3", "Fahrenheit 451", "Ray Bradbury", 1953)
    )

    // Add each book to the library
    initialBooks.forEach { library.addBook(it) }

    // Adding members with different membership types
    library.addMember("M1", "John Doe")
    library.addMember("M2", "Jane Smith", MembershipType.PREMIUM)

    // Demonstrate null safety and smart casts
    println("\nTrying to find a book:")
    val book = library.findBook("1")
    book?.let { println("Found book: ${it.title}") } ?: println("Book not found")

    // Demonstrate sealed class and pattern matching
    println("\nTrying to borrow a book:")
    when (val result = library.borrowBook("M1", "1")) {
        is LoanResult.Success -> println("Successfully borrowed: ${result.book.title}")
        is LoanResult.Error -> println("Failed to borrow: ${result.message}")
    }

    // Demonstrate higher-order functions
    println("\nBooks from the 1950s:")
    library.filterBooks { it.year in 1950..1959 }
        .forEach { println("${it.title} (${it.year})") }

    // Demonstrate collection operations
    println("\nLibrary Statistics:")
    library.getBookStatistics().forEach { (key, value) ->
        println("$key: $value")
    }
}