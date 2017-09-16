package komponent.core

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document

@HtmlTagMarker
interface Element {

	var textContent: String?
		get() = asHtmlElement().textContent
		set(value) { asHtmlElement().textContent = value }

	fun asHtmlElement(): HTMLElement

}

private fun <T : HTMLElement> createElement(name: String,
											parent: Element? = null,
											init: (T.() -> Unit)? = null): T {
	val element: T = document.createElement(name) as T
	init?.let { element.init() }
	parent?.insert(element)
	return element
}

fun Element.insert(vararg elements: HTMLElement) = elements.forEach { this.asHtmlElement().insertBefore(it, null) }
fun Element.insert(vararg elements: Element) = elements.forEach { this.asHtmlElement().insertBefore(it.asHtmlElement(), null) }

fun Element.div(init: (HTMLDivElement.() -> Unit)) = createElement("div", this, init)
fun Element.span(init: (HTMLSpanElement.() -> Unit)) = createElement("span", this, init)
fun Element.button(init: (HTMLButtonElement.() -> Unit)) = createElement("button", this, init)
fun Element.input(init: (HTMLInputElement.() -> Unit)) = createElement("input", this, init)
fun Element.label(init: (HTMLLabelElement.() -> Unit)) = createElement("label", this, init)

