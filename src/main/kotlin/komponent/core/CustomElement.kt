package komponent.core

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRootMode
import kotlin.browser.window
import kotlin.reflect.KClass

abstract class CustomElement : HTMLElement() {

	protected companion object {
		inline fun <reified T : CustomElement> observedAttributes(attributes: Array<String>) {
			addStaticMembersTo<T>(object {
				val observedAttributes = attributes
			})
		}
	}

	@JsName("attributeChangedCallback")
	private fun attributeChangedCallback(name: dynamic, oldValue: dynamic, newValue: dynamic) {
		// Convert from dash case to camelCase
		this.asDynamic()[name] = newValue
	}

	fun render(init: HTMLElement.() -> Unit) {
		val shadowRoot = attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
		js("var forwarder = { insertBefore: function(node) { shadowRoot.appendChild(node); } };")
		js("init(forwarder);")
	}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

}

fun <T : Element> defineElement(name: String, constructor: KClass<T>) {
	window.customElements.define(name, constructor.js.unsafeCast<() -> dynamic>())
}