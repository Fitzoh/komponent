package komponent.polymer.element

import komponent.core.createElement
import komponent.core.insert
import komponent.polymer.behavior.AppScrollEffectsBehavior
import org.w3c.dom.HTMLElement

external abstract class AppHeaderElement : PolymerElement, AppScrollEffectsBehavior {

	var condenses: Boolean

	var fixed: Boolean

	var reveals: Boolean

	var shadow: Boolean

	fun getScrollState(): dynamic

	fun willCondense(): Boolean

}

fun HTMLElement.appHeader(init: (AppHeaderElement.() -> Unit)? = null) = createElement("app-header", this, init)

fun AppHeaderElement.sticky(element: HTMLElement) = insert(element.apply { setAttribute("sticky", "true") })