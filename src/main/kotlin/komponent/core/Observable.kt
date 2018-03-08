package komponent.core

interface Observable<out T> {

	fun get(): T

	fun subscribe(listener: Listener<T>): Subscription

}

interface Subscription {

	fun cancel()

}

class ObservableImpl<T>(initialValue: T) : Observable<T> {

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

	fun set(newValue: T) {
		if (value != newValue) {
			value = newValue
			listeners.forEach { it(newValue) }
		}
	}

}