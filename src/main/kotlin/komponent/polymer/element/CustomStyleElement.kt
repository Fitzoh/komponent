package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

abstract external class CustomStyleElement : PolymerElement

fun HTMLElement.customStyle(init: (CustomStyleElement.() -> Unit)? = null) = createElement("custom-style", this, init)