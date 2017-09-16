package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class AppToolbarElement : PolymerElement

fun HTMLElement.appToolbar(init: (AppToolbarElement.() -> Unit)? = null) = createElement("app-toolbar", this, init)

fun AppToolbarElement.mainTitle(element: HTMLElement) = element.setAttribute("main-title", "true")
fun AppToolbarElement.condensedTitle(element: HTMLElement) = element.setAttribute("condensed-title", "true")
fun AppToolbarElement.bottomItem(element: HTMLElement) = element.setAttribute("bottom-item", "true")
fun AppToolbarElement.topItem(element: HTMLElement) = element.setAttribute("top-item", "true")
fun AppToolbarElement.spacer(element: HTMLElement) = element.setAttribute("spacer", "true")