package komponent.polymer.behavior

import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.mutableProperty
import komponent.property.MutableProperty

external interface IronButtonState : IronA11yKeysBehavior {

	var ariaActiveAttribute: String

	val pointerDown: Boolean

	var pressed: Boolean

	var receivedFocusFromKeyboard: Boolean

	var toggles: Boolean

}

fun IronButtonState.active(): MutableProperty<Boolean> = asPolymerElement().mutableProperty("active")