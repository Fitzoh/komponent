package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.on
import komponent.example.Styles
import komponent.polymer.element.paperButton
import org.w3c.dom.HTMLElement

abstract class CounterButton : CustomElement() {
	companion object {
		const val tag = "counter-button"
		fun define() = defineElement<CounterButton>(tag)
	}

	private var count by property(0)
	var onCountChanged by propertyCallback(::count)

	init {
		render {
			paperButton {
				Styles.accentBackground(this)

				// Change button text when count changes
				subscribe(::count) { textContent = "Increase ($it)" }

				// Increment counter on click
				on("click") {
					count += 1
				}
			}
		}
	}

}

fun HTMLElement.counterButton(init: (CounterButton.() -> Unit)? = null) = createElement(CounterButton.tag, this, init)