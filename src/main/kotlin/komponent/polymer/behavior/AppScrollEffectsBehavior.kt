package komponent.polymer.behavior

import komponent.polymer.element.asPolymerElement
import komponent.polymer.element.property

external interface AppScrollEffectsBehavior : IronScrollTargetBehavior {

	var disabled: Boolean

	var effects: String?

	var effectsConfig: dynamic

	var threshold: Int

	fun createEffect(effectName: String, effectConfig: dynamic): dynamic

	fun isContentBelow(): Boolean

	fun isOnScreen(): Boolean

}

fun AppScrollEffectsBehavior.thresholdTriggered() = asPolymerElement().property<Boolean>("thresholdTriggered")