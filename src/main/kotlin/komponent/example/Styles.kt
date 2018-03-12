package komponent.example

import org.w3c.dom.css.ElementCSSInlineStyle

object Styles {

	fun primaryBackground(element: ElementCSSInlineStyle) {
		element.style.apply {
			backgroundColor = Colors.primary
			color = "white"
		}
	}

	fun accentBackground(element: ElementCSSInlineStyle) {
		element.style.apply {
			backgroundColor = Colors.accent
			color = "white"
		}
	}

}