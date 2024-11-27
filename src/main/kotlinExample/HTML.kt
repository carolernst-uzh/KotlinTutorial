// The task is to create a DSL in Kotlin that generates the following HTML page:
//
//<html>
//    <head>
//        <title>
//            Title
//        </title>
//    </head>
//    <body>
//        <h1>Heading 1</h1>
//        <p>Paragraph 1</p>
//        <p>Paragraph 2</p>
//        <ul>
//            <li>List item 1</li>
//            <li>List item 2</li>
//            <li>List item 3</li>
//        </ul>
//    </body>
//</html>

package kotlinExample


// Base class for all HTML elements
open class HtmlElement(private val tag: String) {
    val children = mutableListOf<HtmlElement>()
    var text: String? = null

    fun addChild() {
        //
    }

    override fun toString(): String {
        val childrenString = children.joinToString("\n") { it.toString() }
        val content = listOfNotNull(text, childrenString).joinToString("\n")

        return "<$tag>\n$content\n</$tag>"
    }
}

// Subclasses for specific HTML elements
class Title {
    // Can contain only simple text
}

class Head {
    // Can contain Title
}

class ListItem {
    // Can contain only simple text
}

class UnorderedList {
    // Can contain ListItem
}

class H1 {
    // Can contain only simple text
}

class Paragraph {
    // Can contain only simple text
}

class Body {
    // Can contain Paragraph and UnorderedList
}

class Html {
    // Can contain Head and Body

}

// DSL entry point
fun html(): Html {
    //
    return Html()
}

// Usage example
fun main() {
//    val document = html {
//        head {
//            title {
//                text = "Title"
//            }
//        }
//        body {
//            h1 {
//                text = "Heading 1"
//            }
//            p {
//                text = "Paragraph 1"
//            }
//            p {
//                text = "Paragraph 2"
//            }
//            ul {
//                li {
//                    text = "List item 1"
//                }
//                li {
//                    text = "List item 2"
//                }
//                li {
//                    text = "List item 3"
//                }
//            }
//        }
//    }
//    println(document)
}









//fun main() {
//    val document = html {
//        head {
//             title {
//                text = "Title"
//            }
//        }
//        body {
//            val paragraphs = listOf("Paragraph 1", "Paragraph 2")
//            paragraphs.forEach { el ->
//                p {
//                    text = el
//                }
//            }
//            ul {
//                val listItems = listOf("List item 1", "List item 2", "List item 3")
//                listItems.forEach { el ->
//                    li {
//                        text = el
//                    }
//                }
//            }
//        }
//    }
//    println(document)
//}
