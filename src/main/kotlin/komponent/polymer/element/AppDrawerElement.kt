package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

abstract external class AppDrawerElement : PolymerElement {

	var align: String

	var disableSwipe: Boolean

	var noFocusTrap: Boolean

	var persistent: Boolean

	val position: String

	var swipeOpen: Boolean

	var transitionDuration: Int

	fun close()

	fun getWidth()

	fun open()

	fun resetLayout()

	fun toggle()

}

val AppDrawerElement.opened get() = mutableProperty<Boolean>("opened")

fun HTMLElement.appDrawer(init: (AppDrawerElement.() -> Unit)? = null) = createElement("app-drawer", this, init)