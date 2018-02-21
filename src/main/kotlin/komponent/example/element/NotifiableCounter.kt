package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.on
import komponent.core.style
import komponent.event.Notification
import komponent.example.SharedStyles
import komponent.polymer.element.paperButton
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class NotifiableCounter : CustomElement() {
	companion object {
		const val tag = "notifiable-counter"
		fun define() = defineElement(tag, NotifiableCounter::class)
	}

	private var count by property(0)

	init {
		render {
			style {
				SharedStyles.accent
			}
			paperButton {
				addClass("accent")
				raised = true

				// Change button text when count changes
				subscribe(::count) { textContent = "Show notification $it" }

				// Show notification on click and increment counter
				on("click") {
					Notification("This is the notification n°$count").send()
					count += 1
				}
			}
		}
	}

}

fun HTMLElement.notifiableCounter(init: (NotifiableCounter.() -> Unit)? = null) = createElement(NotifiableCounter.tag, this, init)