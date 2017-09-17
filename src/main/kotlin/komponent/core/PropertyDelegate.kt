package komponent.core

import komponent.property.MutableProperty
import kotlin.reflect.KProperty

class PropertyDelegate<T>(private val delegate: MutableProperty<T>) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
		return delegate.get()
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
		delegate.set(value)
	}
}