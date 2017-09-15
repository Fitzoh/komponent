package komponent.material

import komponent.core.addClassOnCondition
import komponent.core.createElement
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

external abstract class MaterialButtonElement : HTMLButtonElement

fun HTMLElement.button(init: (MaterialButtonElement.() -> Unit)? = null): MaterialButtonElement {
	val button = createElement("button", this, init).apply {
		addClass(
				"mdl-button",
				"mdl-js-button",
				"mdl-js-ripple-effect"
		)
	}
	upgradeElements(button) // required according to the doc of MDL
	return button
}

fun MaterialButtonElement.primary(value: Boolean = true) = addClassOnCondition("mdl-button--primary", value)
fun MaterialButtonElement.ripple(value: Boolean = true) = addClassOnCondition("mdl-js-ripple-effect", value)
fun MaterialButtonElement.accent(value: Boolean = true) = addClassOnCondition("mdl-button--accent", value)
fun MaterialButtonElement.colored(value: Boolean = true) = addClassOnCondition("mdl-button--colored", value)

// Those four are mutually exclusive
fun MaterialButtonElement.raised(value: Boolean = true) = addClassOnCondition("mdl-button--raised", value)
fun MaterialButtonElement.fab(value: Boolean = true) = addClassOnCondition("mdl-button--fab", value)
fun MaterialButtonElement.minifab(value: Boolean = true) = addClassOnCondition("mdl-button--mini-fab", value)
fun MaterialButtonElement.icon(value: Boolean = true) = addClassOnCondition("mdl-button--icon", value)
