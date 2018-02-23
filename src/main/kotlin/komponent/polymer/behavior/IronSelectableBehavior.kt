package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.propertyCallbackDelegate
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


var <T> IronSelectableBehavior<T>.onSelectedChanged: Listener<Any?>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	set(value) = asPolymerElement().propertyCallbackDelegate<Any?>("selected").setValue(this, this::selected, value)
//val <T> IronSelectableBehavior<T>.items get() = asPolymerElement().property<List<T>>("items")
//val <T> IronSelectableBehavior<T>.selected get() = asPolymerElement().mutableProperty<Any?>("selected").map({ it?.toString() }, { it })
//val <T> IronSelectableBehavior<T>.selectedItem get() = asPolymerElement().property<T?>("selectedItem")