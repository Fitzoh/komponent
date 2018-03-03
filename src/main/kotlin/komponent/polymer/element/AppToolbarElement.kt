package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

abstract external class AppToolbarElement : PolymerElement

fun HTMLElement.appToolbar(init: (AppToolbarElement.() -> Unit)? = null) = createElement("app-toolbar", this, init)

var AppToolbarElement.mainTitle: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("main-heading", "")
var AppToolbarElement.condensedTitle: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("condensed-heading", "")
var AppToolbarElement.bottomItem: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("bottom-item", "")
var AppToolbarElement.topItem: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("top-item", "")
var AppToolbarElement.spacer: HTMLElement
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("spacer", "")