package komponent.polymer.element

import komponent.core.Listener
import komponent.core.Observable
import komponent.core.ObservableCallbackDelegate
import komponent.core.Subscription
import komponent.core.fromCamelToDashCase
import komponent.core.lazy
import org.w3c.dom.HTMLElement

abstract external class PolymerElement : HTMLElement

private fun <T> PolymerElement.observable(name: String): Observable<T> = lazy(name) { PolymerObservable(this, name) }
internal fun <T> PolymerElement.observableCallbackDelegate(name: String): ObservableCallbackDelegate<T> = ObservableCallbackDelegate(observable(name))

internal fun Any.asPolymerElement(): PolymerElement = this.asDynamic()

private open class PolymerObservable<out T>(protected val delegate: dynamic,
											protected val property: String) : Observable<T> {

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