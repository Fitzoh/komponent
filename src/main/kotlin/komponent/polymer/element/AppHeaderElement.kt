package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.AppScrollEffectsBehavior
import org.w3c.dom.Element
import org.w3c.dom.Node

abstract external class AppHeaderElement : PolymerElement, AppScrollEffectsBehavior {

	var condenses: Boolean

	var fixed: Boolean

	var reveals: Boolean

	var shadow: Boolean

	fun getScrollState(): dynamic

	fun willCondense(): Boolean

}

var AppHeaderElement.sticky: Element
	get() = throw UnsupportedOperationException()
	set(element) = element.setAttribute("sticky", "true")

fun Node.appHeader(init: (AppHeaderElement.() -> Unit)? = null) = createElement("app-header", this, init)