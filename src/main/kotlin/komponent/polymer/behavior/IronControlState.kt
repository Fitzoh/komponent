package komponent.polymer.behavior

import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.mutableProperty
import komponent.polymer.element.property

external interface IronControlState

fun IronControlState.disabled() = asPolymerElement().mutableProperty<Boolean>("disabled")
fun IronControlState.focused() = asPolymerElement().property<Boolean>("focused")