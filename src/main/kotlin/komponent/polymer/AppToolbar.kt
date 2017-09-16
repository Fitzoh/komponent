package komponent.polymer

import komponent.core.Element
import komponent.core.createElement
import komponent.core.insert
import org.w3c.dom.HTMLElement

interface AppToolbar : Element

fun HTMLElement.toolbar(init: (AppToolbar.() -> Unit)? = null): AppToolbar = AppToolbarImpl().also { init?.invoke(it); this.insert(it) }
fun Element.toolbar(init: (AppToolbar.() -> Unit)? = null): AppToolbar = AppToolbarImpl().also { init?.invoke(it); this.insert(it) }

fun AppToolbar.mainTitle(element: HTMLElement) = insert(element.apply { setAttribute("main-title", "true") })
fun AppToolbar.mainTitle(element: Element) = insert(element.apply { asHtmlElement().setAttribute("main-title", "true") })
fun AppToolbar.condensedTitle(element: HTMLElement) = insert(element.apply { setAttribute("condensed-title", "true") })
fun AppToolbar.condensedTitle(element: Element) = insert(element.apply { asHtmlElement().setAttribute("condensed-title", "true") })
fun AppToolbar.bottomItem(element: HTMLElement) = insert(element.apply { setAttribute("bottom-item", "true") })
fun AppToolbar.bottomItem(element: Element) = insert(element.apply { asHtmlElement().setAttribute("bottom-item", "true") })
fun AppToolbar.topItem(element: HTMLElement) = insert(element.apply { setAttribute("top-item", "true") })
fun AppToolbar.topItem(element: Element) = insert(element.apply { asHtmlElement().setAttribute("top-item", "true") })
fun AppToolbar.spacer(element: HTMLElement) = insert(element.apply { setAttribute("spacer", "true") })
fun AppToolbar.spacer(element: Element) = insert(element.apply { asHtmlElement().setAttribute("spacer", "true") })

private class AppToolbarImpl : AppToolbar {

	private val delegate = createElement<HTMLElement>("app-toolbar")

	override fun asHtmlElement(): HTMLElement = delegate

}
