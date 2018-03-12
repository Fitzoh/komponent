package komponent.core

import org.w3c.dom.HTMLElement
import org.w3c.dom.Node
import org.w3c.dom.OPEN
import org.w3c.dom.ShadowRootMode
import kotlin.browser.window
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

abstract class CustomElement(private val createShadowRoot: Boolean = true) : HTMLElement() {

	protected companion object {
		inline fun <reified T : CustomElement> observedAttributes(vararg attributes: String) {
			addStaticMembersTo<T>(object {
				val observedAttributes = attributes.map { it.fromCamelToDashCase() }.toTypedArray()
			})
		}
	}

	private val delegatesMap = hashMapOf<String, ObservableDelegate<*>>()

	init {
		if (createShadowRoot) {
			attachShadow(ShadowRootInit(ShadowRootMode.OPEN))
		}
	}

	@JsName("attributeChangedCallback")
	protected open fun attributeChangedCallback(name: String, oldValue: dynamic, newValue: dynamic) {
		val camelCasedName = name.fromDashToCamelCase()
		this.asDynamic()[camelCasedName] = newValue
	}

	@JsName("connectedCallback")
	protected open fun connectedCallback() {
		val receiver: Node = if (createShadowRoot) {
			shadowRoot!!
		} else {
			this
		}
		receiver.render()
	}

	@JsName("disconnectedCallback")
	protected open fun disconnectedCallback() {
	}

	@JsName("adoptedCallback")
	protected open fun adoptedCallback() {
	}

	protected open fun Node.render() {}

	private class ShadowRootInit(override var mode: ShadowRootMode?) : org.w3c.dom.ShadowRootInit

	protected fun <T> observable(initialValue: T, reflectToAttributes: Boolean = false) = ObservableDelegateLoader(initialValue, reflectToAttributes)

	protected fun <T> observableCallback(property: KProperty0<T>) = ObservableCallbackDelegate(getObservable(property))

	protected fun <T> subscribe(property: KProperty0<T>, listener: Listener<T>): Subscription {
		return getObservable(property).subscribe(listener)
	}

	private fun <T> getObservable(property: KProperty0<T>): Observable<T> {
		val propertyName = property.name
		val delegate = delegatesMap[propertyName] ?: throw IllegalArgumentException("$propertyName is not observable")
		return delegate.delegate as Observable<T>
	}

	protected inner class ObservableDelegateLoader<T>(private val initialValue: T,
													  private val reflectToAttributes: Boolean) {
		operator fun provideDelegate(thisRef: CustomElement, prop: KProperty<*>): ReadWriteProperty<CustomElement, T> {
			val delegate = ObservableDelegate(initialValue, reflectToAttributes)
			delegatesMap[prop.name] = delegate
			return delegate
		}
	}

	private class ObservableDelegate<T>(initialValue: T,
										private val reflectToAttributes: Boolean) : ReadWriteProperty<CustomElement, T> {
		val delegate = ObservableImpl(initialValue)

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