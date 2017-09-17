package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.div
import komponent.core.h1
import komponent.core.p
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class DummyCard : CustomElement() {
	companion object {
		fun define() = defineElement("dummy-card", DummyCard::class)
	}

	var heading: String
	var number: Int

	init {
		heading = "Title"
		number = 0

		initShadowRoot {
			div {
				addClass("card")
				div { addClass("circle"); textContent = number.toString() }
				h1 { textContent = heading }
				notifiableCounter()
				p {
					textContent = """
								|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
								|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
								|Cu mei vide viris gloriatur, at populo eripuit sit.
								|""".trimMargin()
				}
			}
		}
	}

}

fun HTMLElement.dummyCard(init: (DummyCard.() -> Unit)? = null) = createElement("dummy-card", this, init)