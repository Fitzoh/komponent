package komponent.core

import addStaticMembersTo
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRootMode
import kotlin.browser.window
import kotlin.reflect.KClass

abstract class CustomElement : HTMLElement() {

	protected companion object {
		inline fun <reified T : Any> observedAttributes(attributes: Array<String>) {
			addStaticMembersTo<T>(object {
				val observedAttributes = attributes
			})
		}
	}

	@JsName("attributeChangedCallback")
	protected fun attributeChangedCallback(name: dynamic, oldValue: dynamic, newValue: dynamic) {
		// Convert from dash case to camelCase
		this.asDynamic()[name] = newValue
	}

	protected open fun HTMLElement.render() {}

	fun render() {
		createShadowRoot().render()
	}

	private fun createShadowRoot(): HTMLElement {
		val shadowRoot = attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
		js("var forwarder = { insertBefore: function(node) { shadowRoot.appendChild(node); } };")
		return js("forwarder")
	}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

}

fun <T : Element> defineElement(name: String, constructor: KClass<T>) {
	window.customElements.define(name, constructor.js.unsafeCast<() -> dynamic>())
}

fun <T : CustomElement> createCustomElement(name: String,
											parent: HTMLElement? = null,
											init: (T.() -> Unit)? = null) = createElement(name, parent, init).apply { render() }