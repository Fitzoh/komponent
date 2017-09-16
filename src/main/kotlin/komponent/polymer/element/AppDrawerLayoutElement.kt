package komponent.polymer.element

import komponent.core.createElement
import komponent.core.lazy
import org.w3c.dom.HTMLElement

external abstract class AppDrawerLayoutElement : PolymerElement {

	var forceNarrow: Boolean

	var openedWhenNarrow: Boolean

	var responsiveWidth: String

}

fun HTMLElement.appDrawerLayout(init: (AppDrawerLayoutElement.() -> Unit)? = null) = createElement("app-drawer-layout", this, init)

fun AppDrawerLayoutElement.drawer(init: (AppDrawerElement.() -> Unit)? = null): AppDrawerElement = lazy("drawer") { appDrawer() }.also {
	it.setAttribute("slot", "drawer")
	init?.invoke(it)
}

fun AppDrawerLayoutElement.drawerToggle(element: HTMLElement) = element.setAttribute("drawer-toggle", "true")

fun AppDrawerLayoutElement.narrow() = property<Boolean>("narrow")
