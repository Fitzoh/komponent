package komponent.property

import komponent.core.Subscription

interface Property<out T> {

	fun get(): T

	fun subscribe(listener: (T) -> Unit): Subscription

}