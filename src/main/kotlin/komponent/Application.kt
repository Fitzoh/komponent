package komponent

import komponent.core.div
import komponent.polymer.paperInput
import komponent.property.bind
import kotlin.browser.document

fun main(args: Array<String>) {
	document.body!!.div {
        val first = paperInput().value
        val second = paperInput().value
		first.bind(second)
		second.subscribe { println(it) }
	}
}

data class TestEvent(val name: String)