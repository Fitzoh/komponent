package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.propertyCallbackDelegate

external interface IronControlState {

	var disabled: Boolean

	val focused: Boolean

}

var IronControlState.onDisabledChanged: Listener<Boolean>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	set(value) = asPolymerElement().propertyCallbackDelegate<Boolean>("disabled").setValue(this, ::disabled, value)

var IronControlState.onFocusedChanged: Listener<Boolean>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	set(value) = asPolymerElement().propertyCallbackDelegate<Boolean>("focused").setValue(this, ::focused, value)