package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.observableCallbackDelegate

external interface IronMultiSelectableBehavior<out T> : IronSelectableBehavior<T> {

	var multi: Boolean

	val selectedItems: List<T>

	var selectedValues: List<Any>

}

var <T> IronMultiSelectableBehavior<T>.onSelectedItemsChanged: Listener<List<T>>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<List<T>>("selectedItems").setValue(this, ::selectedItems, value)

var <T> IronMultiSelectableBehavior<T>.onSelectedValuesChanged: Listener<List<Any>>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<List<Any>>("selectedValues").setValue(this, ::selectedValues, value)