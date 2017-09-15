package komponent.core

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

fun <T : HTMLElement> createElement(name: String,
									parent: HTMLElement? = null,
									init: (T.() -> Unit)? = null): T {
	val element: T = document.createElement(name) as T
	init?.let { element.init() }
	parent?.insert(element)
	return element
}

fun HTMLElement.insert(vararg elements: HTMLElement) = elements.forEach { this.insertBefore(it, null) }
fun HTMLElement.div(init: (HTMLDivElement.() -> Unit)) = createElement("div", this, init)
fun HTMLElement.span(init: (HTMLSpanElement.() -> Unit)) = createElement("span", this, init)
fun HTMLElement.button(init: (HTMLButtonElement.() -> Unit)) = createElement("button", this, init)
fun HTMLElement.input(init: (HTMLInputElement.() -> Unit)) = createElement("input", this, init)
fun HTMLElement.label(init: (HTMLLabelElement.() -> Unit)) = createElement("label", this, init)

fun HTMLElement.addClassOnCondition(className: String, condition: Boolean) {
	if (condition) {
		addClass(className)
	} else {
		removeClass(className)
	}
}