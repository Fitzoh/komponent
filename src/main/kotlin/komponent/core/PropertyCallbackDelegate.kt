package komponent.core

import komponent.property.Property
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PropertyCallbackDelegate<T>(private val property: Property<T>) : ReadWriteProperty<Any?, Listener<T>?> {
	private var subscription: Subscription? = null
	override fun getValue(thisRef: Any?, property: KProperty<*>): Listener<T>? {
		throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	}

	override fun setValue(thisRef: Any?, property: KProperty<*>, value: Listener<T>?) {
		subscription?.cancel()
		subscription = null
		if (value != null) {
			subscription = this.property.subscribe { value(it) }
		}
	}
}