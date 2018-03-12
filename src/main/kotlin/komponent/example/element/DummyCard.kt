package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.div
import komponent.core.h1
import komponent.core.p
import komponent.core.style
import org.w3c.dom.Node

abstract class DummyCard : CustomElement() {

	companion object {
		const val tag = "dummy-card"
		fun define() = defineElement<DummyCard>(tag)
		init {
			observedAttributes<DummyCard>("heading", "number")
		}
	}

	var heading: String by observable("Heading")
	var number: Int by observable(0)

	override fun Node.render() {
		style { textContent = """
				|:host {
				|	display: block;
				|	margin: 16px;
				|	padding: 16px;
				|	background-color: white;
				|	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
				|}
			""".trimMargin()
		}

		div {
			subscribe(::number) { textContent = it.toString() }
			style.apply {
				display = "inline-block"
				width = "64px"
				height = "64px"
				textAlign = "center"
				borderRadius = "50%"
				backgroundColor = "#DDDDDD"
				fontSize = "30px"
				lineHeight = "64px"
			}
		}

		h1 {
			subscribe(::heading) { textContent = it }
		}

		val text = p {}
		counterButton {
			onCountChanged = {
				text.textContent =  "Count received from child : $it"
			}
		}
	}
}

fun Node.dummyCard(init: (DummyCard.() -> Unit)? = null) = createElement(DummyCard.tag, this, init)