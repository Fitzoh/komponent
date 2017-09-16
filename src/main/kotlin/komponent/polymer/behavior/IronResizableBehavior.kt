package komponent.polymer.behavior

import org.w3c.dom.HTMLElement

external interface IronResizableBehavior {

	fun assignParentResizable(parentResizable: HTMLElement)

	fun notifyResize()

	fun resizerShouldNotify(element: IronResizableBehavior): Boolean

	fun stopResizeNotificationsFor(element: HTMLElement)

}