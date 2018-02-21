package komponent.example

import azadev.kotlin.css.Stylesheet
import azadev.kotlin.css.backgroundColor
import azadev.kotlin.css.color

object SharedStyles {

	const val accentClass = "accent"
	fun Stylesheet.accent() {
		c(accentClass) {
			backgroundColor = "var(--accent-color)"
			color = "white"
		}
	}

}