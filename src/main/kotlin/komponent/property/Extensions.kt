package komponent.property

fun <A, B> Property<A>.map(transform: (A) -> B): Property<B> {
	val prop = Prop(transform(get()))
	this.subscribe { prop.set(transform(it)) }
	return prop
}

fun <A> Property<A>.bind(other: MutableProperty<A>) {
	subscribe { other.set(it) }
}