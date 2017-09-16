package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.PaperButtonBehavior
import org.w3c.dom.HTMLElement

abstract external class PaperButtonElement : PolymerElement, PaperButtonBehavior {

	var raised: Boolean

}

fun HTMLElement.paperButton(init: (PaperButtonElement.() -> Unit)? = null) = createElement("paper-button", this, init)