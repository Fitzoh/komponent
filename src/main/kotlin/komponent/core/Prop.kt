package komponent.core

class Prop<T>(initialValue: T) : MutableProperty<T> {

	private var value = initialValue
	private val listeners = LinkedHashSet<Listener<T>>()

	override fun get(): T = value

	override fun subscribe(listener: Listener<T>): Subscription {
		listeners.add(listener)
		listener(get())
		return object : Subscription {
			override fun cancel() {
				listeners.remove(listener)
			}
		}
	}

	override fun set(newValue: T) {
		val oldValue = value
		if (oldValue != newValue) {
			value = newValue
			listeners.forEach { it(newValue) }
		}
	}

}