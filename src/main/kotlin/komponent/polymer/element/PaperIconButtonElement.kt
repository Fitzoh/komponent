package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.IronButtonState
import komponent.polymer.behavior.IronControlState
import komponent.polymer.behavior.PaperRippleBehavior
import org.w3c.dom.HTMLElement

external abstract class PaperIconButtonElement : PolymerElement, IronButtonState, IronControlState, PaperRippleBehavior {

	var icon: String?

	var src: String?

	var alt: String?

}

fun HTMLElement.paperIconButton(init: (PaperIconButtonElement.() -> Unit)? = null) = createElement("paper-icon-button", this, init)