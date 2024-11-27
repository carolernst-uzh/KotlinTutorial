package kotlinExample


fun main() {
    val str = "Kotlin"
    //println(str.lastChar())

    val people = listOf(Person("Alice", 29), Person("Bob", 46))
    findTheOldest(people)
    people.maxBy({ p: Person -> p.age })

}

// Extension function
//fun String.lastChar(): Char {
//    return this.get(this.length - 1)
//}


// Lambdas
data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>): Person? {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    return theOldest
}


// Lambdas with receiver
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun alphabetApply() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        this.append(letter)
    }
    this.append("\nNow I know the alphabet!")
}.toString()
