package komponent.example

import komponent.core.div
import komponent.example.element.DummyCard
import komponent.example.element.NotifiableCounter
import komponent.example.element.dummyCard
import komponent.polymer.Polymer
import komponent.property.MutableProperty
import kotlin.browser.document

fun main(args: Array<String>) {
	defineElements()
	Polymer.install()

	var selectedTab: MutableProperty<String?>? = null
	document.body!!.div {
		dummyCard { heading = "View One"; number = 1 }
//		appDrawerLayout {
//			drawer {
//				appToolbar { textContent = "Menu" }
//				ironSelector<HTMLDivElement> {
//					addClass("drawer-list")
//					div { textContent = "View One" }
//					div { textContent = "View Two" }
//					div { textContent = "View Three" }
//				}.let { selectedTab = it.selected }
//			}
//
//			appHeaderLayout {
//				header {
//					appToolbar {
//						this@appDrawerLayout.drawerToggle = paperIconButton { icon = "komponent:menu"}
//						mainTitle = div { textContent = "My App" }
//					}
//				}
//
//				ironPages<DummyCard> {
//					dummyCard { title = "View One" ; number = 1 }
//					dummyCard { title = "View Two" ; number = 2 }
//					dummyCard { title = "View Three" ; number = 3 }
//				}.let { selectedTab!!.bind(it.selected) }
//			}
//		}
	}

	// Open first tab
//	selectedTab!!.set("0")
}

fun defineElements() {
	DummyCard.define()
	NotifiableCounter.define()
}