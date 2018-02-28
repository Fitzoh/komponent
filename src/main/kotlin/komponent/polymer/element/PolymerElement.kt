package komponent.polymer.element

import komponent.core.Listener
import komponent.core.PropertyCallbackDelegate
import komponent.core.Subscription
import komponent.core.fromCamelToDashCase
import komponent.core.lazy
import komponent.property.Property
import org.w3c.dom.HTMLElement

abstract external class PolymerElement : HTMLElement

internal fun <T> PolymerElement.propertyCallbackDelegate(name: String): PropertyCallbackDelegate<T> = PropertyCallbackDelegate(property(name))
private fun <T> PolymerElement.property(name: String): Property<T> = lazy(name) { PolymerProperty(this, name) }

internal fun Any.asPolymerElement(): PolymerElement = this.asDynamic()

private open class PolymerProperty<out T>(protected val delegate: dynamic,
										  protected val property: String) : Property<T> {

	private val listeners = LinkedHashSet<Listener<T>>()

	init {
		val dashCasedProperty = property.fromCamelToDashCase()
		delegate.addEventListener("$dashCasedProperty-changed", { event ->
			listeners.forEach { it(event.detail.value as T) }
		})
	}

	override fun get(): T = delegate[property] as T

	override fun subscribe(listener: Listener<T>): Subscription {
		listeners.add(listener)
		return object : Subscription {
			override fun cancel() {
				listeners.remove(listener)
			}
		}
	}

}