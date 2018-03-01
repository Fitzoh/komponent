package komponent.example.element

import azadev.kotlin.css.BLOCK
import azadev.kotlin.css.CENTER
import azadev.kotlin.css.INLINE_BLOCK
import azadev.kotlin.css.WHITE
import azadev.kotlin.css.backgroundColor
import azadev.kotlin.css.borderRadius
import azadev.kotlin.css.boxShadow
import azadev.kotlin.css.color
import azadev.kotlin.css.dimens.box
import azadev.kotlin.css.dimens.percent
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
			println("hello")
			observedAttributes<DummyCard>("heading", "number")
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
			val text = p {}
			counterButton {
				onCountChanged = {
					text.textContent =  "Count received from child : $it"
				}
			}

			// CSS
			style {
				":host" {
					display = BLOCK
					margin = 24.px
					padding = 16.px
					color = 0x757575
					backgroundColor = WHITE
					boxShadow = "0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2)"
				}

				c(circleClass) {
					display = INLINE_BLOCK
					width = 64.px
					height = 64.px
					textAlign = CENTER
					color = 0x555
					borderRadius = 50.percent
					backgroundColor = 0xdddddd
					fontSize = 30.px
					lineHeight = 64.px
				}

				h1 {
					margin = box(16.px, 0)
					color = 0x212121
					fontSize = 22.px
				}
			}
		}
	}

}

fun HTMLElement.dummyCard(init: (DummyCard.() -> Unit)? = null) = createElement(DummyCard.tag, this, init)