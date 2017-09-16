package komponent.polymer.behavior

import org.w3c.dom.HTMLElement

external interface IronScrollTargetBehavior {

	var scrollTarget: HTMLElement?

	fun scroll(left: Int, right: Int)

	fun toggleScrollListener(addEvent: Boolean)

}