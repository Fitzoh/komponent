package komponent.polymer.behavior

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

external interface PaperRippleBehavior {

	var noink: Boolean?

	fun ensureRipple(optTriggeringEvent: Event?)

	fun getRipple(): HTMLElement

	fun hasRipple(): Boolean

}