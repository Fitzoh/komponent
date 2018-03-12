package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.Node

abstract external class CustomStyleElement : PolymerElement

fun Node.customStyle(init: (CustomStyleElement.() -> Unit)? = null) = createElement("custom-style", this, init)