package komponent.material

import komponent.core.createElement
import komponent.core.insert
import komponent.property.Prop
import komponent.property.Property
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLabelElement
import kotlin.dom.addClass

data class MaterialTextFieldElement(val container: MaterialTextFieldContainerElement,
									val input: MaterialInputElement,
									val label: MaterialLabelElement)

external abstract class MaterialInputElement : HTMLInputElement
external abstract class MaterialLabelElement : HTMLLabelElement
external abstract class MaterialTextFieldContainerElement : HTMLDivElement

fun HTMLElement.textField(id: String, label: String): MaterialTextFieldElement {
	val inputElement = createElement<MaterialInputElement>("input") {
		addClass("mdl-textfield__input")
		type = "text"
		this.id = id
	}
	val labelElement = createElement<MaterialLabelElement>("label") {
		addClass("mdl-textfield__label")
		htmlFor = id
		textContent = label
	}
	val containerElement = createElement<MaterialTextFieldContainerElement>("div") {
		addClass("mdl-textfield", "mdl-js-textfield", "mdl-textfield--floating-label")
		insert(inputElement, labelElement)
	}
	upgradeElements(inputElement, labelElement, containerElement)
	insert(containerElement)
	return MaterialTextFieldElement(
			container = containerElement,
			input = inputElement,
			label = labelElement
	)
}

fun MaterialTextFieldElement.value(): Property<String?> {
	val prop = Prop<String?>(null)
	input.addEventListener("input", {
		val value = input.value
		if (value.isNotBlank()) {
			prop.set(input.value)
		} else {
			prop.set(null)
		}
		println(it)
	})
	return prop
}