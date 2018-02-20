package komponent.core

import komponent.property.Property
import kotlin.reflect.KProperty

class PropertyListenerDelegate<T>(private val property: Property<T>) {
	private var listener: Listener<T>? = null
	private var subscription: Subscription? = null

	operator fun getValue(thisRef: Any?, property: KProperty<*>): Listener<T>? {
		return listener
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Listener<T>?) {
		listener = value
		if (value == null) {
			subscription?.cancel()
		}
		if (value != null) {
			subscription = this.property.subscribe { this.listener?.invoke(it) }
		}
	}
}