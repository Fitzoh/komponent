package komponent

import komponent.core.div
import komponent.polymer.appDrawerLayout
import komponent.polymer.drawer
import komponent.polymer.ironSelector
import komponent.polymer.toolbar
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document

fun main(args: Array<String>) {
	document.body!!.div {
		appDrawerLayout {
			drawer {
				toolbar {
					div {
						textContent = "Menu"
					}
				}
				div { textContent = "hello world" }
				ironSelector<HTMLDivElement> {
					div { textContent = "first" }
					div { textContent = "second" }
					div { textContent = "third" }
				}
			}
		}
	}
}