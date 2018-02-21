package komponent.core

import komponent.property.MutableProperty
import komponent.property.Prop
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRootMode
import kotlin.browser.window
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

abstract class CustomElement : HTMLElement() {

	protected companion object {
		@PublishedApi
		internal val camelToDashRegex = Regex("([a-zA-Z])(?=[A-Z])")
		private val dashToCamelRegex = Regex("-([a-z])?")

		inline fun <reified T : CustomElement> observedAttributes(attributes: Array<String>) {
			addStaticMembersTo<T>(object {
				val observedAttributes = attributes.map {
					it.replace(camelToDashRegex, "$1-").toLowerCase()
				}.toTypedArray()
			})
		}
	}

	private val delegatesMap = hashMapOf<String, PropertyDelegate<*>>()

	@JsName("attributeChangedCallback")
	private fun attributeChangedCallback(name: String, oldValue: dynamic, newValue: dynamic) {
		val camelCasedName = name.replace(dashToCamelRegex) { it.groups[1]?.value?.toUpperCase() ?: "" }
		this.asDynamic()[camelCasedName] = newValue
	}

	protected fun render(init: HTMLElement.() -> Unit) {
		val shadowRoot = attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
		js("var forwarder = { insertBefore: function(node) { shadowRoot.appendChild(node); } };")
		js("init(forwarder);")
	}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

	protected fun <T> subscribe(property: KProperty0<T>, listener: Listener<T>): Subscription {
		val propertyName = property.name
		val delegate = delegatesMap[propertyName] ?: throw IllegalArgumentException("$propertyName is not a property")
		return (delegate.delegate as Prop<T>).subscribe(listener)
	}

	protected fun <T> property(initialValue: T) = PropertyLoader(initialValue)

	protected inner class PropertyLoader<T>(private val initialValue: T) {
		operator fun provideDelegate(thisRef: CustomElement, prop: KProperty<*>): ReadWriteProperty<CustomElement, T> {
			val delegate = PropertyDelegate(Prop(initialValue))
			delegatesMap[prop.name] = delegate
			return delegate
		}
	}

	private class PropertyDelegate<T>(val delegate: MutableProperty<T>): ReadWriteProperty<CustomElement, T> {
		override fun getValue(thisRef: CustomElement, property: KProperty<*>): T {
			return delegate.get()
		}

		override fun setValue(thisRef: CustomElement, property: KProperty<*>, value: T) {
			delegate.set(value)
		}
	}

}

fun <T : Element> defineElement(name: String, constructor: KClass<T>) {
	window.customElements.define(name, constructor.js.unsafeCast<() -> dynamic>())
}