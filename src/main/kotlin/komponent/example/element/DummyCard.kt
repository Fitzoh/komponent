package komponent.example.element

import azadev.kotlin.css.backgroundColor
import azadev.kotlin.css.borderRadius
import azadev.kotlin.css.boxShadow
import azadev.kotlin.css.color
import azadev.kotlin.css.dimens.px
import azadev.kotlin.css.display
import azadev.kotlin.css.fontSize
import azadev.kotlin.css.height
import azadev.kotlin.css.lineHeight
import azadev.kotlin.css.margin
import azadev.kotlin.css.padding
import azadev.kotlin.css.textAlign
import azadev.kotlin.css.width
import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.div
import komponent.core.h1
import komponent.core.p
import komponent.core.style
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class DummyCard : CustomElement() {

	companion object {
		const val tag = "dummy-card"
		fun define() = defineElement(tag, DummyCard::class)

		init {
			observedAttributes<DummyCard>(arrayOf("heading", "number"))
		}
	}

	var heading: String by property("Heading")
	var number: Int by property(0)

	init {
		render {
			val circleClass = "circle"

			// DOM
			div {
				addClass(circleClass)
				subscribe(::number) { textContent = it.toString() }
			}
			h1 {
				subscribe(::heading) { textContent = it }
			}
			notifiableCounter {
				onCountChanged = { println("Received count from child: $it") }
			}
			p {
				textContent = """
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Cu mei vide viris gloriatur, at populo eripuit sit.
							|""".trimMargin()
			}

			// CSS
			style {
				":host" {
					display = "block"
					margin = 24.px
					padding = 16.px
					color = 0x757575
					backgroundColor = 0xffffff
					boxShadow = "0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2)"
				}

				c(circleClass) {
					display = "inline-block"
					width = 64.px
					height = 64.px
					textAlign = "center"
					color = 0x555
					borderRadius = "50%"
					backgroundColor = 0xdddddd
					fontSize = 30.px
					lineHeight = 64.px
				}

				h1 {
					margin = "16px 0"
					color = 0x212121
					fontSize = 22.px
				}
			}
		}
	}

}

fun HTMLElement.dummyCard(init: (DummyCard.() -> Unit)? = null) = createElement(DummyCard.tag, this, init)