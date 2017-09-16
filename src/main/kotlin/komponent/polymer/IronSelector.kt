package komponent.polymer

import komponent.core.Element
import komponent.core.createElement
import komponent.core.insert
import komponent.property.MutableProperty
import komponent.property.Property
import komponent.property.map
import org.w3c.dom.HTMLElement

interface IronSelector<out T> : Element {

	var attrForSelected: String?

	val items: Property<List<T>>

	val selected: MutableProperty<String?>

	val selectedItem: Property<T?>

}

fun <T> HTMLElement.selector(init: (IronSelector<T>.() -> Unit)? = null): IronSelector<T> = IronSelectorImpl<T>().also { init?.invoke(it) ; this.insert(it) }
fun <T> Element.selector(init: (IronSelector<T>.() -> Unit)? = null): IronSelector<T> = IronSelectorImpl<T>().also { init?.invoke(it) ; this.insert(it) }

private class IronSelectorImpl<out T> : IronSelector<T> {

	private val delegate = createElement<HTMLElement>("iron-selector")

	override var attrForSelected by PolymerVariable<String?>(delegate, "attrForSelected")
	override val items: Property<List<T>> by lazy { PolymerProperty<List<T>>(delegate, "items") }
	override val selected: MutableProperty<String?> by lazy { PolymerMutableProperty<Any?>(delegate, "selected").map({ it?.toString() }, { it }) }
	override val selectedItem: Property<T?> by lazy { PolymerProperty<T?>(delegate, "selectedItem") }

	override fun asHtmlElement(): HTMLElement = delegate

}