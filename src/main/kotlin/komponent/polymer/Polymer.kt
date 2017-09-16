package komponent.polymer

import komponent.core.createElement
import komponent.event.Events
import komponent.event.Notification
import komponent.polymer.element.PaperToastElement
import komponent.polymer.element.ToastProperties
import komponent.polymer.element.paperButton
import kotlin.browser.document

object Polymer {

	fun install() {
		installNotificationListener()
	}

	private fun installNotificationListener() {
		val toast = createElement<PaperToastElement>("paper-toast", document.body!!) {
			paperButton {
				textContent = "Ok"
				style.setProperty("color", "#eeff41")
				onclick = { this@createElement.hide() }
			}
		}
		Events.subscribe<Notification> { (message, duration) ->
			toast.hide()
			toast.show(ToastProperties(text = message, duration = duration ?: 0))
		}
	}

}