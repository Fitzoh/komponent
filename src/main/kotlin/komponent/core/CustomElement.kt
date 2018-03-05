package komponent.core

import org.w3c.dom.HTMLElement
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRootMode
import kotlin.browser.window
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

abstract class CustomElement : HTMLElement {

	protected companion object {
		inline fun <reified T : CustomElement> observedAttributes(vararg attributes: String) {
			addStaticMembersTo<T>(object {
				val observedAttributes = attributes.map { it.fromCamelToDashCase() }.toTypedArray()
			})
		}
	}

	private val renders: Boolean
	private val delegatesMap = hashMapOf<String, PropertyDelegate<*>>()

	constructor(renders: Boolean = true) : super() {
		this.renders = renders
		if (renders) {
			attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
		}
	}

	@JsName("attributeChangedCallback")
	private fun attributeChangedCallback(name: String, oldValue: dynamic, newValue: dynamic) {
		val camelCasedName = name.fromDashToCamelCase()
		this.asDynamic()[camelCasedName] = newValue
	}

	@JsName("connectedCallback")
	protected fun connectedCallback() {
		if (renders) {
			val shadowRoot = shadowRoot!!
			val rendering: (HTMLElement) -> Unit = { renderIn(it) }
			js("var forwarder = { insertBefore: function(node) { shadowRoot.appendChild(node); } };")
			js("rendering(forwarder);")
		}
	}

	@JsName("disconnectedCallback")
	protected fun disconnectedCallback() {
	}

	@JsName("adoptedCallback")
	protected fun adoptedCallback() {
	}

	protected abstract fun HTMLElement.render()

	private fun renderIn(parent: HTMLElement) {
		parent.render()
	}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

	protected fun <T> property(initialValue: T, reflectToAttributes: Boolean = false) = PropertyLoader(initialValue, reflectToAttributes)

	protected fun <T> propertyCallback(property: KProperty0<T>) = PropertyCallbackDelegate(getMutableProperty(property))

	protected fun <T> subscribe(property: KProperty0<T>, listener: Listener<T>): Subscription {
		return getMutableProperty(property).subscribe(listener)
	}

	private fun <T> getMutableProperty(property: KProperty0<T>): MutableProperty<T> {
		val propertyName = property.name
		val delegate = delegatesMap[propertyName] ?: throw IllegalArgumentException("$propertyName is not a property")
		return delegate.delegate as MutableProperty<T>
	}

	protected inner class PropertyLoader<T>(private val initialValue: T,
											private val reflectToAttributes: Boolean) {
		operator fun provideDelegate(thisRef: CustomElement, prop: KProperty<*>): ReadWriteProperty<CustomElement, T> {
			val delegate = PropertyDelegate(Prop(initialValue), reflectToAttributes)
			delegatesMap[prop.name] = delegate
			return delegate
		}
	}

	private class PropertyDelegate<T>(val delegate: Prop<T>,
									  private val reflectToAttributes: Boolean) : ReadWriteProperty<CustomElement, T> {

		override fun getValue(thisRef: CustomElement, property: KProperty<*>): T {
			return delegate.get()
		}

		override fun setValue(thisRef: CustomElement, property: KProperty<*>, value: T) {
			if (reflectToAttributes) {
				thisRef.setAttribute(property.name, value.toString())
			}
			delegate.set(value)
		}
	}

}

inline fun <reified T : CustomElement> defineElement(tag: String) {
	window.customElements.define(tag, T::class.js.unsafeCast<() -> dynamic>())
}