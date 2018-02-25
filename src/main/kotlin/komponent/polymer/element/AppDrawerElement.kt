package komponent.polymer.element

import komponent.core.Listener
import komponent.core.createElement
import org.w3c.dom.HTMLElement

abstract external class AppDrawerElement : PolymerElement {

	var align: String

	var disableSwipe: Boolean

	var opened: Boolean

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

var AppDrawerElement.onOpenedChanged: Listener<Boolean>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	set(value) = asPolymerElement().propertyCallbackDelegate<Boolean>("opened").setValue(this, ::opened, value)

fun HTMLElement.appDrawer(init: (AppDrawerElement.() -> Unit)? = null) = createElement("app-drawer", this, init)