package komponent.example

import komponent.core.div
import komponent.example.element.DummyCard
import komponent.example.element.NotifiableCounter
import komponent.example.element.dummyCard
import komponent.polymer.Polymer
import komponent.polymer.behavior.selected
import komponent.polymer.element.appDrawerLayout
import komponent.polymer.element.appHeaderLayout
import komponent.polymer.element.appToolbar
import komponent.polymer.element.drawer
import komponent.polymer.element.drawerToggle
import komponent.polymer.element.header
import komponent.polymer.element.ironPages
import komponent.polymer.element.ironSelector
import komponent.polymer.element.mainTitle
import komponent.polymer.element.paperIconButton
import komponent.property.MutableProperty
import komponent.property.bind
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.dom.addClass

fun main(args: Array<String>) {
	defineElements()
	Polymer.install()

	var selectedTab: MutableProperty<String?>? = null
	document.body!!.div {
		appDrawerLayout {
			drawer {
				appToolbar { textContent = "Menu" }
				ironSelector<HTMLDivElement> {
					addClass("drawer-list")
					div { textContent = "View One" }
					div { textContent = "View Two" }
					div { textContent = "View Three" }
				}.let { selectedTab = it.selected }
			}

			appHeaderLayout {
				header {
					appToolbar {
						this@appDrawerLayout.drawerToggle = paperIconButton { icon = "komponent:menu"}
						mainTitle = div { textContent = "My App" }
					}
				}

				ironPages<DummyCard> {
					dummyCard { heading = "View One" ; number = 1 }
					dummyCard { heading = "View Two" ; number = 2 }
					dummyCard { heading = "View Three" ; number = 3 }
				}.let { selectedTab!!.bind(it.selected) }
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