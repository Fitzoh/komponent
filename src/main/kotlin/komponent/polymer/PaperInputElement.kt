package komponent.polymer

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class PaperInputElement : PolymerElement {

	var disabled: Boolean

	var label: String?

}

fun HTMLElement.paperInput(init: (PaperInputElement.() -> Unit)? = null) = createElement("paper-input", this, init)

fun PaperInputElement.value() = mutableProperty<String?>("value")