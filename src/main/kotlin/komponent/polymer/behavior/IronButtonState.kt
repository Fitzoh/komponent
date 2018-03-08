package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.observableCallbackDelegate

external interface IronButtonState : IronA11yKeysBehavior {

	var active: Boolean

	var ariaActiveAttribute: String

	val pointerDown: Boolean

	var pressed: Boolean

	var receivedFocusFromKeyboard: Boolean

	var toggles: Boolean

}

var IronButtonState.IronButtonState: Listener<Boolean>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated value to call it instead.")
	set(value) = asPolymerElement().observableCallbackDelegate<Boolean>("active").setValue(this, ::active, value)