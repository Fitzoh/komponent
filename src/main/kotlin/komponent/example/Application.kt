package komponent.example

import komponent.core.div
import komponent.core.on
import komponent.example.element.DummyCard
import komponent.example.element.NotifiableCounter
import komponent.example.element.dummyCard
import komponent.polymer.Polymer
import komponent.polymer.behavior.onSelectedChanged
import komponent.polymer.element.AppDrawerLayoutElement
import komponent.polymer.element.IronSelectorElement
import komponent.polymer.element.PaperIconButtonElement
import komponent.polymer.element.appDrawerLayout
import komponent.polymer.element.appHeaderLayout
import komponent.polymer.element.appToolbar
import komponent.polymer.element.drawer
import komponent.polymer.element.header
import komponent.polymer.element.ironPages
import komponent.polymer.element.ironSelector
import komponent.polymer.element.mainTitle
import komponent.polymer.element.paperIconButton
import komponent.polymer.element.paperItem
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.dom.addClass

fun main(args: Array<String>) {
	defineElements()
	Polymer.install()
	renderApplication()
}

private fun renderApplication() {
	var menuButton: PaperIconButtonElement? = null
	var drawerLayout: AppDrawerLayoutElement? = null
	var tabSelector: IronSelectorElement<HTMLDivElement>? = null

	document.body!!.appHeaderLayout {
		header {
			fixed = true
			shadow = true
			appToolbar {
				menuButton = paperIconButton { icon = "komponent:menu" }
				mainTitle = div { textContent = "Komponent" }
			}
		}

		drawerLayout = appDrawerLayout {
			drawer {
				div {
					addClass("drawer-list")
					tabSelector = ironSelector {
						paperItem { textContent = "View One" }
						paperItem { textContent = "View Two" }
						paperItem { textContent = "View Three" }
					}
				}
			}

			val ironPages = ironPages<DummyCard> {
				dummyCard { heading = "View One"; number = 1 }
				dummyCard { heading = "View Two"; number = 2 }
				dummyCard { heading = "View Three"; number = 3 }
			}
			tabSelector!!.onSelectedChanged = { ironPages.selected = it }
		}
	}

	// Open first tab
	tabSelector!!.selected = "0"

	// Handle drawer layout menu button
	menuButton!!.on("click") {
		val theDrawerLayout = drawerLayout!!
		if (theDrawerLayout.forceNarrow || !theDrawerLayout.narrow) {
			theDrawerLayout.forceNarrow = !theDrawerLayout.forceNarrow
		} else {
			theDrawerLayout.drawer.toggle()
		}
	}
}

fun defineElements() {
	DummyCard.define()
	NotifiableCounter.define()
}