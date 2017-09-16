package komponent.polymer

import komponent.core.createElement
import komponent.property.map
import org.w3c.dom.HTMLElement

external abstract class IronSelectorElement<T> : PolymerElement {

	var attrForSelected: String?

}

fun <T> HTMLElement.ironSelector(init: (IronSelectorElement<T>.() -> Unit)? = null) = createElement("iron-selector", this, init)

fun <T> IronSelectorElement<T>.items() = property<List<T>>("items")
fun <T> IronSelectorElement<T>.selected() = mutableProperty<Any?>("selected").map({ it?.toString() }, { it })
fun <T> IronSelectorElement<T>.selectedItem() = property<T?>("selectedItem")