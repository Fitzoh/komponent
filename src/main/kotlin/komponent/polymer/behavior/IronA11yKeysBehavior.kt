package komponent.polymer.behavior

import org.w3c.dom.events.EventTarget
import org.w3c.dom.events.KeyboardEvent

external interface IronA11yKeysBehavior {

	var keyBindings: dynamic

	var keyEventTarget: EventTarget?

	var stopKeyboardEventPropagation: Boolean

	fun addOwnKeyBinding(eventString: String, handlerName: String)

	fun keyboardEventMatchesKeys(event: KeyboardEvent, eventString: String): Boolean

	fun removeOwnKeyBindings()

}