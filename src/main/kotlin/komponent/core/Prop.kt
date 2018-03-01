package komponent.core

class Prop<T>(initialValue: T) : MutableProperty<T> {

	private var value = initialValue
	private val listeners = LinkedHashSet<(T) -> Unit>()

	override fun get(): T = value

	override fun subscribe(listener: (T) -> Unit): Subscription {
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