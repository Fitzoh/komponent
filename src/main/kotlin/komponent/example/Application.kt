package komponent.example

import komponent.example.element.DummyCard
import komponent.example.element.NotifiableCounter
import komponent.polymer.Polymer

fun main(args: Array<String>) {
	defineElements()
	Polymer.install()

//	var selectedTab: MutableProperty<String?>? = null
//	var menuButton: PaperIconButtonElement? = null
//
//	document.body!!.div {
//		appHeaderLayout {
//			header {
//				fixed = true
//				shadow = true
//				appToolbar {
//					paperIconButton { icon = "komponent:menu" }.let { menuButton = it }
//					mainTitle = div { textContent = "Komponent" }
//				}
//			}
//			appDrawerLayout {
//				drawer {
//					div {
//						addClass("drawer-list")
//						ironSelector<HTMLDivElement> {
//							paperItem { textContent = "View One" }
//							paperItem { textContent = "View Two" }
//							paperItem { textContent = "View Three" }
//						}.let { selectedTab = it.selected }
//					}
//				}
//
//				ironPages<DummyCard> {
//					dummyCard { heading = "View One"; number = 1 }
//					dummyCard { heading = "View Two"; number = 2 }
//					dummyCard { heading = "View Three"; number = 3 }
//				}.let { selectedTab!!.bind(it.selected) }
//			}.let { drawerLayout ->
//				menuButton!!.on("click") {
//					if (drawerLayout.forceNarrow || !drawerLayout.narrow.get()) {
//						drawerLayout.forceNarrow = !drawerLayout.forceNarrow
//					} else {
//						drawerLayout.drawer.toggle()
//					}
//				}
//			}
//		}
//	}
//
//	// Open first tab
//	selectedTab!!.set("0")
}

fun defineElements() {
	DummyCard.define()
	NotifiableCounter.define()
}