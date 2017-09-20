package komponent.example.element

import komponent.core.CustomElement
import komponent.core.PropertyDelegate
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.div
import komponent.core.h1
import komponent.core.p
import komponent.core.style
import komponent.property.Prop
import org.w3c.dom.HTMLElement
import kotlin.dom.addClass

abstract class DummyCard : CustomElement() {
	companion object {
		fun define() = defineElement("dummy-card", DummyCard::class)
		init {
			observedAttributes<DummyCard>(arrayOf("heading", "number"))
		}
	}

	private var _heading = Prop("Heading")
	private var _number = Prop(0)

	var heading: String by PropertyDelegate(_heading)
	var number: Int by PropertyDelegate(_number)

	init {
		shadowRoot {
			div {
				addClass("circle")
				_number.subscribe { textContent = number.toString() }
			}
			h1 { _heading.subscribe { textContent = it } }
			notifiableCounter()
			p {
				textContent = """
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Cu mei vide viris gloriatur, at populo eripuit sit.
							|""".trimMargin()
			}

			style {
				"""
				|:host {
				|	display: block;
				|	margin: 24px;
				|	padding: 16px;
				|	color: #757575;
				|	background-color: #fff;
				|	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
				|}
				|
				|.circle {
				|	display: inline-block;
				|	width: 64px;
				|	height: 64px;
				|	text-align: center;
				|	color: #555;
				|	border-radius: 50%;
				|	background: #ddd;
				|	font-size: 30px;
				|	line-height: 64px;
				|}
				|
				|h1 {
				|	margin: 16px 0;
				|	color: #212121;
				|	font-size: 22px;
				|}""".trimMargin()
			}
		}
	}
}

fun HTMLElement.dummyCard(init: (DummyCard.() -> Unit)? = null) = createElement("dummy-card", this, init)