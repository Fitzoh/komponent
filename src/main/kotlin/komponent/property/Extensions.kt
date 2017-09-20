package komponent.property

import kotlin.reflect.KMutableProperty0

fun <A, B> Property<A>.map(transform: (A) -> B): Property<B> {
	val prop = Prop(transform(get()))
	this.subscribe { prop.set(transform(it)) }
	return prop
}

fun <A, B> MutableProperty<A>.map(transform1: (A) -> B, transform2: (B) -> A): MutableProperty<B> {
	val prop = Prop(transform1(get()))
	this.subscribe { prop.set(transform1(it)) }
	prop.subscribe { this.set(transform2(it)) }
	return prop
}

fun <A> Property<A>.bind(to: MutableProperty<A>) {
	subscribe { to.set(it) }
}

fun <A> Property<A>.bind(to: KMutableProperty0<A>) {
	this.subscribe { to.set(it) }
}

fun <A, B> Property<A>.bind(to: KMutableProperty0<B>, transform: (A) -> B) {
	this.subscribe { to.set(transform(it)) }
}

fun <A> MutableProperty<A>.immutable(): Property<A> = object : Property<A> by this {}