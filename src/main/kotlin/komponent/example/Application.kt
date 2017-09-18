package komponent.example

import komponent.core.div
import komponent.core.on
import komponent.example.element.DummyCard
import komponent.example.element.NotifiableCounter
import komponent.example.element.dummyCard
import komponent.polymer.Polymer
import komponent.polymer.behavior.selected
import komponent.polymer.element.PaperIconButtonElement
import komponent.polymer.element.appDrawerLayout
import komponent.polymer.element.appHeaderLayout
import komponent.polymer.element.appToolbar
import komponent.polymer.element.drawer
import komponent.polymer.element.header
import komponent.polymer.element.ironPages
import komponent.polymer.element.ironSelector
import komponent.polymer.element.mainTitle
import komponent.polymer.element.narrow
import komponent.polymer.element.paperIconButton
import komponent.polymer.element.paperItem
import komponent.property.MutableProperty
import komponent.property.bind
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.dom.addClass

fun main(args: Array<String>) {
	defineElements()
	Polymer.install()

	var selectedTab: MutableProperty<String?>? = null
	var menuButton: PaperIconButtonElement? = null

	document.body!!.div {
		appHeaderLayout {
			header {
				fixed = true
				shadow = true
				appToolbar {
					paperIconButton { icon = "komponent:menu" }.let { menuButton = it }
					mainTitle = div { textContent = "Ozone Admin" }
				}
			}
			appDrawerLayout {
				drawer {
					div {
						addClass("drawer-list")
						ironSelector<HTMLDivElement> {
							paperItem { textContent = "View One" }
							paperItem { textContent = "View Two" }
							paperItem { textContent = "View Three" }
						}.let { selectedTab = it.selected }
					}
				}

				ironPages<DummyCard> {
					dummyCard { heading = "View One"; number = 1 }
					dummyCard { heading = "View Two"; number = 2 }
					dummyCard { heading = "View Three"; number = 3 }
				}.let { selectedTab!!.bind(it.selected) }
			}.let { drawerLayout ->
				menuButton!!.on("click") {
					if (drawerLayout.forceNarrow || !drawerLayout.narrow.get()) {
						drawerLayout.forceNarrow = !drawerLayout.forceNarrow
					} else {
						drawerLayout.drawer.toggle()
					}
				}
			}
		}
	}

	// Open first tab
	selectedTab!!.set("0")
}

fun defineElements() {
	DummyCard.define()
	NotifiableCounter.define()
}