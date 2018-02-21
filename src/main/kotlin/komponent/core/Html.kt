package komponent.core

import azadev.kotlin.css.Stylesheet
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import kotlin.browser.document

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
fun HTMLElement.a(init: (HTMLAnchorElement.() -> Unit)) = createElement("a", this, init)
fun HTMLElement.p(init: (HTMLParagraphElement.() -> Unit)) = createElement("p", this, init)
fun HTMLElement.h1(init: (HTMLHeadElement.() -> Unit)) = createElement("h1", this, init)
fun HTMLElement.h2(init: (HTMLHeadElement.() -> Unit)) = createElement("h2", this, init)
fun HTMLElement.h3(init: (HTMLHeadElement.() -> Unit)) = createElement("h3", this, init)
fun HTMLElement.h4(init: (HTMLHeadElement.() -> Unit)) = createElement("h4", this, init)
fun HTMLElement.h5(init: (HTMLHeadElement.() -> Unit)) = createElement("h5", this, init)
fun HTMLElement.h6(init: (HTMLHeadElement.() -> Unit)) = createElement("h6", this, init)

fun HTMLElement.style(init: (Stylesheet.() -> Unit)? = null) = createElement<HTMLStyleElement>("style", this) {
	textContent = Stylesheet(init).render()
}

fun EventTarget.on(type: String, handler: (Event) -> Unit) = this.addEventListener(type, handler)

fun <T> HTMLElement.lazy(name: String, supplier: () -> T): T {
	val dyn: dynamic = this
	if (dyn.__lazy__ == null) {
		dyn.__lazy__ = hashMapOf<String, Any>()
	}

	var value: T? = dyn.__lazy__[name] as T?
	if (value != null) {
		return value
	}

	value = supplier()
	dyn.__lazy__[name] = value
	return value
}