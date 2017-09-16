package komponent.polymer.element

import komponent.core.createElement
import komponent.core.lazy
import komponent.polymer.behavior.AppLayoutBehavior
import org.w3c.dom.HTMLElement

abstract external class AppHeaderLayoutElement : PolymerElement, AppLayoutBehavior {

	var hasScrollingRegion: Boolean

}

fun HTMLElement.appHeaderLayout(init: (AppHeaderLayoutElement.() -> Unit)? = null) = createElement("app-header-layout", this, init)

fun AppHeaderLayoutElement.header(init: (AppHeaderElement.() -> Unit)? = null): AppHeaderElement = lazy("drawer") { appHeader() }.also {
	it.setAttribute("slot", "header")
	init?.invoke(it)
}