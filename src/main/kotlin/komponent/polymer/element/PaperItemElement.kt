package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.PaperItemBehavior
import org.w3c.dom.HTMLElement

abstract external class PaperItemElement : PolymerElement, PaperItemBehavior

fun HTMLElement.paperItem(init: (PaperItemElement.() -> Unit)? = null) = createElement("paper-item", this, init)