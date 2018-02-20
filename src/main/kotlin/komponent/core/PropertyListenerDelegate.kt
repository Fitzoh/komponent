package komponent.core

import komponent.property.Property
import kotlin.reflect.KProperty

class PropertyListenerDelegate<T>(private val property: Property<T>) {
	private var subscription: Subscription? = null

	operator fun getValue(thisRef: Any?, property: KProperty<*>): Listener<T>? {
		throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Listener<T>?) {
		subscription?.cancel()
		subscription = null
		if (value != null) {
			subscription = this.property.subscribe { value(it) }
		}
	}
}