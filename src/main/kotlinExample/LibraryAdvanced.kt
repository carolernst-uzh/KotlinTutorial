import Library

// Demonstration of collection operations as extension function to library
fun Library.getBookStatistics(): Map<String, Any> {
    return mapOf(
        "totalBooks" to books.size,
        "availableBooks" to books.count { !it.borrowed },
        "borrowedBooks" to books.count { it.borrowed },
        "authorStats" to books.groupBy { it.author }
            .mapValues { it.value.size }
    )
}

fun main() {
    val library = Library()

    // Demonstrate collection operations
    println("\nLibrary Statistics:")
    library.getBookStatistics().forEach { (key, value) ->
        println("$key: $value")
    }
}