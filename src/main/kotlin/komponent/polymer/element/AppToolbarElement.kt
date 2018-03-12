package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.Element
import org.w3c.dom.Node

abstract external class AppToolbarElement : PolymerElement

fun Node.appToolbar(init: (AppToolbarElement.() -> Unit)? = null) = createElement("app-toolbar", this, init)

var AppToolbarElement.mainTitle: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("main-heading", "")
var AppToolbarElement.condensedTitle: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("condensed-heading", "")
var AppToolbarElement.bottomItem: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("bottom-item", "")
var AppToolbarElement.topItem: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("top-item", "")
var AppToolbarElement.spacer: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("spacer", "")