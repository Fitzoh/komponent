package komponent.polymer.element

import komponent.core.HtmlTagMarker
import komponent.core.Subscription
import komponent.core.lazy
import komponent.property.MutableProperty
import komponent.property.Property
import org.w3c.dom.HTMLElement

@HtmlTagMarker
external abstract class PolymerElement : HTMLElement

internal fun <T> PolymerElement.property(name: String): Property<T> = lazy(name) { PolymerProperty(this, name) }
internal fun <T> PolymerElement.mutableProperty(name: String): MutableProperty<T> = lazy(name) { PolymerMutableProperty(this, name) }

internal fun Any.asPolymerElement(): PolymerElement = this.asDynamic()

private open class PolymerProperty<out T>(private val delegate: dynamic,
										  private val property: String) : Property<T> {

	private val listeners = LinkedHashSet<(T) -> Unit>()

	init {
		val dashCasedProperty = property.replace(Regex("[A-Z]"), { "-${it.groupValues[0].toLowerCase()}" })
		delegate.addEventListener("$dashCasedProperty-changed", { event ->
			listeners.forEach { it(event.detail.value as T) }
		})
	}

	override fun get(): T = delegate[property] as T

	override fun subscribe(listener: (T) -> Unit): Subscription {
		listeners.add(listener)
		return object : Subscription {
			override fun cancel() {
				listeners.remove(listener)
			}
		}
	}

}

private class PolymerMutableProperty<T>(private val delegate: dynamic,
										private val property: String) : MutableProperty<T>, PolymerProperty<T>(delegate, property) {

	override fun set(newValue: T) {
		delegate[property] = newValue
	}

}