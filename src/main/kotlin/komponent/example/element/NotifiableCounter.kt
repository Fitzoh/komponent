package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.event.Notification
import komponent.polymer.element.paperButton
import komponent.property.MutableProperty
import komponent.property.Prop
import komponent.property.Property
import komponent.property.immutable
import org.w3c.dom.HTMLElement

abstract class NotifiableCounter : CustomElement() {
	companion object {
		fun define() = defineElement("notifiable-counter", NotifiableCounter::class)
	}

	private val mutableCount: MutableProperty<Int> = Prop(1)
	val count: Property<Int> = mutableCount.immutable()

	init {
		shadowRoot {
			paperButton {
				raised = true

				// Change button text when count changes
				count.subscribe { this.textContent = "Show notification $it" }

				// Show notification on click and increment counter
				addEventListener("click", {
					Notification("This is the notification n°${count.get()}").send()
					mutableCount.set(mutableCount.get() + 1)
				})
			}
		}
	}

}

fun HTMLElement.notifiableCounter(init: (NotifiableCounter.() -> Unit)? = null) = createElement("notifiable-counter", this, init)