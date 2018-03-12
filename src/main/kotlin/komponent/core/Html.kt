package komponent.core

import org.w3c.dom.Element
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLHeadElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSlotElement
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import kotlin.browser.document


fun <T : Element> createElement(tag: String,
									parent: Node? = null,
									init: (T.() -> Unit)? = null): T {
	val element: T = document.createElement(tag) as T
	init?.let { element.init() }
	parent?.appendChild(element)
	return element
}

// TODO auto generate those
fun Node.div(init: (HTMLDivElement.() -> Unit)) = createElement("div", this, init)
fun Node.span(init: (HTMLSpanElement.() -> Unit)) = createElement("span", this, init)
fun Node.a(init: (HTMLAnchorElement.() -> Unit)) = createElement("a", this, init)
fun Node.p(init: (HTMLParagraphElement.() -> Unit)) = createElement("p", this, init)
fun Node.h1(init: (HTMLHeadElement.() -> Unit)) = createElement("h1", this, init)
fun Node.h2(init: (HTMLHeadElement.() -> Unit)) = createElement("h2", this, init)
fun Node.h3(init: (HTMLHeadElement.() -> Unit)) = createElement("h3", this, init)
fun Node.h4(init: (HTMLHeadElement.() -> Unit)) = createElement("h4", this, init)
fun Node.h5(init: (HTMLHeadElement.() -> Unit)) = createElement("h5", this, init)
fun Node.h6(init: (HTMLHeadElement.() -> Unit)) = createElement("h6", this, init)
fun Node.style(init: (HTMLStyleElement.() -> Unit)? = null) = createElement("style", this, init)
fun Node.slot(init: (HTMLSlotElement.() -> Unit)? = null) = createElement("slot", this, init)
fun Node.text(text: String) = appendChild(document.createTextNode(text))

fun EventTarget.on(type: String, handler: (Event) -> Unit) = this.addEventListener(type, handler)

fun <T> Node.lazy(name: String, supplier: () -> T): T {
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