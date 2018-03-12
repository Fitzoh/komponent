package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.on
import komponent.example.Styles
import komponent.polymer.element.paperButton
import org.w3c.dom.Node

abstract class CounterButton : CustomElement() {
	companion object {
		const val tag = "counter-button"
		fun define() = defineElement<CounterButton>(tag)
	}

	private var count by observable(0)
	var onCountChanged by observableCallback(::count)

	override fun Node.render() {
		paperButton {
			Styles.accentBackground(this)

			// Change button text when count changes
			subscribe(::count) { textContent = "Increase ($it)" }

			// Increment counter on click
			on("tap") {
				count += 1
			}
		}
	}
}

fun Node.counterButton(init: (CounterButton.() -> Unit)? = null) = createElement(CounterButton.tag, this, init)