package komponent.core

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ObservableCallbackDelegate<T>(private val observable: Observable<T>) : ReadWriteProperty<Any?, Listener<T>?> {
	private var subscription: Subscription? = null
	override fun getValue(thisRef: Any?, property: KProperty<*>): Listener<T>? {
		throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	}

	override fun setValue(thisRef: Any?, property: KProperty<*>, value: Listener<T>?) {
		subscription?.cancel()
		subscription = null
		if (value != null) {
			subscription = observable.subscribe { value(it) }
		}
	}
}