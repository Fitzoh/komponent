package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.PaperItemBehavior
import org.w3c.dom.HTMLElement

abstract external class PaperIconItemElement : PolymerElement, PaperItemBehavior

var PaperIconItemElement.icon: String
	get() = throw UnsupportedOperationException()
	set(value) {
		ironIcon {
			setAttribute("slot", "item-icon")
			icon = value
		}
	}

fun HTMLElement.paperIconItem(init: (PaperIconItemElement.() -> Unit)? = null) = createElement("paper-icon-item", this, init)