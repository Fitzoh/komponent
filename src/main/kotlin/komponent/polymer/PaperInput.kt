package komponent.polymer

import komponent.core.DelegatedHTMLElement
import komponent.core.createElement
import komponent.property.MutableProperty
import org.w3c.dom.HTMLElement

interface PaperInput {

    val value: MutableProperty<String>

}

class PaperInputImpl(private val delegate: dynamic) : PaperInput, DelegatedHTMLElement(delegate) {

    override val value by lazy { PolymerMutableProperty<String>(delegate, "value") }

}

fun HTMLElement.paperInput(): PaperInput = PaperInputImpl(createElement("paper-input", this))