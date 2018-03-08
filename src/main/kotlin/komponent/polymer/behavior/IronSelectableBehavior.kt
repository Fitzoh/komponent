package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.observableCallbackDelegate
import org.w3c.dom.HTMLElement

external interface IronSelectableBehavior<out T> {

	var activateEvent: String

	var attrForSelected: String?

	var fallbackSelection: String?

	val items: List<T>

	var selectable: String?

	var selected: Any? // String or Int

	var selectedAttribute: String?

	val selectedItem: T?

	var selectedClass: String

	fun forceSynchronousItemUpdate()

	fun indexOf(item: HTMLElement): Int

	fun selectIndex(index: Int)

	fun selectNext()

	fun selectPrevious()

}


var <T> IronSelectableBehavior<T>.onItemsChanged: Listener<List<T>>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<List<T>>("items").setValue(this, ::items, value)
var <T> IronSelectableBehavior<T>.onSelectedChanged: Listener<Any?>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<Any?>("selected").setValue(this, ::selected, value)
var <T> IronSelectableBehavior<T>.onSelectedItemChanged: Listener<T?>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<T?>("selectedItem").setValue(this, ::selectedItem, value)