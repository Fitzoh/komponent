package komponent.polymer.element

import komponent.core.createElement
import komponent.core.lazy
import org.w3c.dom.HTMLElement

abstract external class AppDrawerLayoutElement : PolymerElement {

	var forceNarrow: Boolean

	var openedWhenNarrow: Boolean

	var responsiveWidth: String

}

val AppDrawerLayoutElement.narrow get() = property<Boolean>("narrow")
val AppDrawerLayoutElement.drawer get() = drawer()
fun AppDrawerLayoutElement.drawer(init: (AppDrawerElement.() -> Unit)? = null): AppDrawerElement = lazy("drawer") { appDrawer() }.also {
	it.setAttribute("slot", "drawer")
	init?.invoke(it)
}
var AppDrawerLayoutElement.drawerToggle: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("drawer-toggle", "")

fun HTMLElement.appDrawerLayout(init: (AppDrawerLayoutElement.() -> Unit)? = null) = createElement("app-drawer-layout", this, init)

