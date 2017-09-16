package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class PaperIconButtonElement : PolymerElement {

	var icon: String

}

fun HTMLElement.paperIconButton(icon: String, init: (PaperIconButtonElement.() -> Unit)? = null) = createElement("paper-icon-button", this, init).also { it.icon = icon }