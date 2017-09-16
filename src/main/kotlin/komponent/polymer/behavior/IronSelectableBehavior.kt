package komponent.polymer.behavior

import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.mutableProperty
import komponent.polymer.element.property
import komponent.property.map
import org.w3c.dom.HTMLElement

external interface IronSelectableBehavior<T> {

	var activateEvent: String

	var attrForSelected: String?

	var fallbackSelection: String?

	var selectable: String?

	var selectedAttribute: String?

	var selectedClass: String

	fun forceSynchronousItemUpdate()

	fun indexOf(item: HTMLElement): Int

	fun selectIndex(index: Int)

	fun selectNext()

	fun selectPrevious()

}

val <T> IronSelectableBehavior<T>.items get() = asPolymerElement().property<List<T>>("items")
val <T> IronSelectableBehavior<T>.selected get() = asPolymerElement().mutableProperty<Any?>("selected").map({ it?.toString() }, { it })
val <T> IronSelectableBehavior<T>.selectedItem get() = asPolymerElement().property<T?>("selectedItem")