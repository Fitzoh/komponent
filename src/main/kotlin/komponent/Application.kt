package komponent

import komponent.core.div
import komponent.polymer.drawer
import komponent.polymer.drawerLayout
import komponent.polymer.selector
import komponent.polymer.textField
import komponent.polymer.toolbar
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document

fun main(args: Array<String>) {
	document.body!!.div {
		drawerLayout {
			drawer {
				toolbar {
					div {
						textContent = "Menu"
					}
				}
				selector<HTMLDivElement> {
					div { textContent = "first" }
					div { textContent = "second" }
					div { textContent = "third" }
				}
			}
			div {
				textField {
					label = "Your name"
				}
			}
		}
	}
}