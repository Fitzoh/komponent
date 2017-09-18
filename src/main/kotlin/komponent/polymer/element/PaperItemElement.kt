package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.IronButtonState
import komponent.polymer.behavior.IronControlState
import org.w3c.dom.HTMLElement

external abstract class PaperItemElement : PolymerElement, IronControlState, IronButtonState

fun HTMLElement.paperItem(init: (PaperItemElement.() -> Unit)? = null) = createElement("paper-item", this, init)