package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class AppToolbarElement : PolymerElement

fun HTMLElement.appToolbar(init: (AppToolbarElement.() -> Unit)? = null) = createElement("app-toolbar", this, init)

var AppToolbarElement.mainTitle: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("main-title", "true")
var AppToolbarElement.condensedTitle: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("condensed-title", "true")
var AppToolbarElement.bottomItem: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("bottom-item", "true")
var AppToolbarElement.topItem: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("top-item", "true")
var AppToolbarElement.spacer: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("spacer", "true")