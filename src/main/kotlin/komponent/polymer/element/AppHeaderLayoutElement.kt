package komponent.polymer.element

import komponent.core.createElement
import komponent.core.lazy
import komponent.polymer.behavior.AppLayoutBehavior
import org.w3c.dom.Node

abstract external class AppHeaderLayoutElement : PolymerElement, AppLayoutBehavior {

	var hasScrollingRegion: Boolean

}

val AppHeaderLayoutElement.header get() = header()
fun AppHeaderLayoutElement.header(init: (AppHeaderElement.() -> Unit)? = null): AppHeaderElement = lazy("header") { appHeader() }.also {
	it.setAttribute("slot", "header")
	init?.invoke(it)
}

fun Node.appHeaderLayout(init: (AppHeaderLayoutElement.() -> Unit)? = null) = createElement("app-header-layout", this, init)

