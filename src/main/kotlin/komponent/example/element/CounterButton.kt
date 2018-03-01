package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.on
import komponent.core.style
import komponent.example.SharedStyles
import komponent.example.SharedStyles.accent
import komponent.polymer.element.paperButton
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class CounterButton : CustomElement() {
	companion object {
		const val tag = "counter-button"
		fun define() = defineElement(tag, CounterButton::class)
	}

	private var count by property(0)
	var onCountChanged by propertyCallback(::count)

	init {
		render {
			paperButton {
				addClass(SharedStyles.accentClass)
				raised = true

				// Change button text when count changes
				subscribe(::count) { textContent = "Current count is : $it" }

				// Increment counter on click
				on("click") {
					count += 1
				}
			}

			style {
				accent()
			}
		}
	}

}

fun HTMLElement.counterButton(init: (CounterButton.() -> Unit)? = null) = createElement(CounterButton.tag, this, init)