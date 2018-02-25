package komponent.polymer.behavior

import komponent.core.Listener
import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.propertyCallbackDelegate

external interface AppScrollEffectsBehavior : IronScrollTargetBehavior {

	var disabled: Boolean

	var effects: String?

	var effectsConfig: dynamic

	var threshold: Int

	val thresholdTriggered: Boolean

	fun createEffect(effectName: String, effectConfig: dynamic): dynamic

	fun isContentBelow(): Boolean

	fun isOnScreen(): Boolean

}

var AppScrollEffectsBehavior.onThresholdTriggeredChanged: Listener<Boolean>?
	get() = throw UnsupportedOperationException("Can not get listener. Update the associated property to call it instead.")
	set(value) = asPolymerElement().propertyCallbackDelegate<Boolean>("thresholdTriggered").setValue(this, ::thresholdTriggered, value)