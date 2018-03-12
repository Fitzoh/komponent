package komponent.polymer.element

import komponent.core.createElement
import komponent.core.lazy
import org.w3c.dom.Element
import org.w3c.dom.Node

abstract external class AppDrawerLayoutElement : PolymerElement {

	var forceNarrow: Boolean

	val narrow: Boolean

	var openedWhenNarrow: Boolean

	var responsiveWidth: String

}

val AppDrawerLayoutElement.drawer get() = drawer()
fun AppDrawerLayoutElement.drawer(init: (AppDrawerElement.() -> Unit)? = null): AppDrawerElement = lazy("drawer") { appDrawer() }.also {
	it.setAttribute("slot", "drawer")
	init?.invoke(it)
}
var AppDrawerLayoutElement.drawerToggle: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("drawer-toggle", "")

fun Node.appDrawerLayout(init: (AppDrawerLayoutElement.() -> Unit)? = null) = createElement("app-drawer-layout", this, init)

