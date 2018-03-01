package komponent.core

interface Property<out T> {

	fun get(): T

	fun subscribe(listener: (T) -> Unit): Subscription

}

interface MutableProperty<T> : Property<T> {

	fun set(newValue: T)

}

interface Subscription {

	fun cancel()

}