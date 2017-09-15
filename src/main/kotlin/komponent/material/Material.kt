package komponent.material

import org.w3c.dom.HTMLElement

private external val componentHandler: dynamic

fun upgradeElements(vararg elements: HTMLElement) {
	elements.forEach { componentHandler.upgradeElement(it) }
}