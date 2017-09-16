package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class AppDrawerElement : PolymerElement {

	var align: String

	var disableSwipe: Boolean

	var noFocusTrap: Boolean

	var persistent: Boolean

	val position: String

	var swipeOpen: Boolean

	var transitionDuration: Int

}

fun HTMLElement.appDrawer(init: (AppDrawerElement.() -> Unit)? = null) = createElement("app-drawer", this, init)

fun AppDrawerElement.opened() = mutableProperty<Boolean>("opened")