package komponent.example

import org.w3c.dom.HTMLElement

object Styles {

	fun primaryBackground(element: HTMLElement) {
		element.style.apply {
			backgroundColor = Colors.primary
			color = "white"
		}
	}

	fun accentBackground(element: HTMLElement) {
		element.style.apply {
			backgroundColor = Colors.accent
			color = "white"
		}
	}

}