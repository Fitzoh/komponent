package komponent.property

interface MutableProperty<T> : Property<T> {

	fun set(newValue: T)

}