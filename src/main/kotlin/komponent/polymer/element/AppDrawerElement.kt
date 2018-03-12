package komponent.polymer.element

import komponent.core.Listener
import komponent.core.createElement
import org.w3c.dom.Node

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
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<Boolean>("opened").setValue(this, ::opened, value)

fun Node.appDrawer(init: (AppDrawerElement.() -> Unit)? = null) = createElement("app-drawer", this, init)