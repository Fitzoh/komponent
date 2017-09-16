package komponent.polymer

import komponent.core.Subscription
import komponent.property.MutableProperty
import komponent.property.Property
import kotlin.reflect.KProperty

open class PolymerValue<out T>(protected val delegate: dynamic,
							   protected val property: String) {

	operator fun getValue(receiver: Any, ignored: KProperty<*>): T = delegate[property] as T

}

open class PolymerVariable<T>(delegate: dynamic,
							  property: String) : PolymerValue<T>(delegate, property) {

	operator fun setValue(receiver: Any, ignored: KProperty<*>, newValue: T) {
		delegate[property] = newValue
	}

}

open class PolymerProperty<out T>(private val delegate: dynamic,
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

class PolymerMutableProperty<T>(private val delegate: dynamic,
								private val property: String) : MutableProperty<T>, PolymerProperty<T>(delegate, property) {

	override fun set(newValue: T) {
		delegate[property] = newValue
	}

}