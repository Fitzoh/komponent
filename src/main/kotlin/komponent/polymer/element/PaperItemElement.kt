package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.PaperItemBehavior
import org.w3c.dom.Node

abstract external class PaperItemElement : PolymerElement, PaperItemBehavior

fun Node.paperItem(init: (PaperItemElement.() -> Unit)? = null) = createElement("paper-item", this, init)