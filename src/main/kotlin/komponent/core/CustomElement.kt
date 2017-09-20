package komponent.core

import addStaticMembersTo
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRoot
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
		js("this[name] = newValue;")
	}

	protected fun shadowRoot(init: HTMLElement.() -> Unit): ShadowRoot {
		val shadowRoot = attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
        js("var forwarder = { insertBefore: function(node) { shadowRoot.appendChild(node); } };")
        js("init(forwarder);")
		return shadowRoot
	}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

}

fun <T : Element> defineElement(name: String, constructor: KClass<T>) {
	window.customElements.define(name, constructor.js.unsafeCast<() -> dynamic>())
}