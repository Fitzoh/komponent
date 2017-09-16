package komponent.polymer

import komponent.core.createElement
import komponent.core.insert
import org.w3c.dom.HTMLElement

external abstract class AppToolbarElement : PolymerElement

fun HTMLElement.toolbar(init: (AppToolbarElement.() -> Unit)? = null) = createElement("app-toolbar", this, init)

fun AppToolbarElement.mainTitle(element: HTMLElement) = insert(element.apply { setAttribute("main-title", "true") })
fun AppToolbarElement.condensedTitle(element: HTMLElement) = insert(element.apply { setAttribute("condensed-title", "true") })
fun AppToolbarElement.bottomItem(element: HTMLElement) = insert(element.apply { setAttribute("bottom-item", "true") })
fun AppToolbarElement.topItem(element: HTMLElement) = insert(element.apply { setAttribute("top-item", "true") })
fun AppToolbarElement.spacer(element: HTMLElement) = insert(element.apply { setAttribute("spacer", "true") })