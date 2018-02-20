package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createCustomElement
import komponent.core.defineElement
import komponent.core.on
import komponent.core.style
import komponent.event.Notification
import komponent.example.SharedStyles
import komponent.polymer.element.paperButton
import komponent.property.MutableProperty
import komponent.property.Prop
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class NotifiableCounter : CustomElement() {
	companion object {
		fun define() = defineElement("notifiable-counter", NotifiableCounter::class)
	}

	private val _count: MutableProperty<Int> = Prop(1)

	override fun HTMLElement.render() {
		style {
			SharedStyles.accent
		}
		paperButton {
			addClass("accent")
			raised = true

			// Change button text when count changes
			_count.subscribe { this.textContent = "Show notification $it" }

			// Show notification on click and increment counter
			on("click") {
				Notification("This is the notification n°${_count.get()}").send()
				_count.set(_count.get() + 1)
			}
		}
	}
}

fun HTMLElement.notifiableCounter(init: (NotifiableCounter.() -> Unit)? = null) = createCustomElement("notifiable-counter", this, init)