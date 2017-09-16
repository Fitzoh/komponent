package komponent.polymer.behavior

import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.mutableProperty
import komponent.polymer.element.property
import komponent.property.MutableProperty
import komponent.property.map

external interface IronMultiSelectableBehavior<T> : IronSelectableBehavior<T> {

	var multi: Boolean

}

fun <T> IronMultiSelectableBehavior<T>.selectedValues(): MutableProperty<List<String>> {
	return asPolymerElement().mutableProperty<List<Any>>("selectedValues").map({ it.map { it.toString() } }, { it })
}

fun <T> IronMultiSelectableBehavior<T>.selectedItems() = asPolymerElement().property<List<T>>("selectedItems")