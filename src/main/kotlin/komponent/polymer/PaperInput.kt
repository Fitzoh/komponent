package komponent.polymer

import komponent.core.Element
import komponent.core.createElement
import komponent.core.insert
import komponent.property.MutableProperty
import org.w3c.dom.HTMLElement

interface PaperInput : Element {

	var disabled: Boolean

	var label: String?

	val value: MutableProperty<String?>

}

fun HTMLElement.textField(init: (PaperInput.() -> Unit)? = null): PaperInput = PaperInputImpl().also { init?.invoke(it); this.insert(it) }
fun Element.textField(init: (PaperInput.() -> Unit)? = null): PaperInput = PaperInputImpl().also { init?.invoke(it); this.insert(it) }

private class PaperInputImpl : PaperInput {

	private val delegate = createElement<HTMLElement>("paper-input")

	override var disabled by PolymerVariable<Boolean>(delegate, "disabled")
	override var label by PolymerVariable<String?>(delegate, "label")
	override val value by lazy { PolymerMutableProperty<String?>(delegate, "value") }

	override fun asHtmlElement(): HTMLElement = delegate

}