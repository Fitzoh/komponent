package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

abstract external class IronIconElement : PolymerElement {

	var icon: String?

	var src: String?

	var alt: String?

}

fun HTMLElement.ironIcon(init: (IronIconElement.() -> Unit)? = null) = createElement("iron-icon", this, init)