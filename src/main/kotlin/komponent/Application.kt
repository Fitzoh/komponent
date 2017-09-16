package komponent

import komponent.core.div
import komponent.core.h1
import komponent.core.p
import komponent.event.Notification
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
import komponent.polymer.element.paperButton
import komponent.polymer.element.paperIconButton
import komponent.property.MutableProperty
import komponent.property.Prop
import komponent.property.bind
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.dom.addClass

fun main(args: Array<String>) {
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

				ironPages<Any> {
					createCard("View One", 1)
					createCard("View Two", 2)
					createCard("View Three", 3)
				}.let { selectedTab!!.bind(it.selected) }
			}
		}
	}

	// Open first tab
	selectedTab!!.set("0")
}

private fun HTMLElement.createCard(title: String, number: Int) {
	val count = Prop(1)

	div {
		addClass("card")
		div { addClass("circle"); textContent = number.toString() }
		h1 { textContent = title }
		paperButton {
			raised = true
			count.subscribe { this.textContent = "Show notification $it" }
			addEventListener("click", {
				Notification("This is the notification ${count.get()} of view $number").send()
				count.set(count.get() + 1)
			})
		}
		p { textContent = "Ut labores minimum atomorum pro. Laudem tibique ut has." }
		p {
			textContent = """
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea.
							|Cu mei vide viris gloriatur, at populo eripuit sit.
							|""".trimMargin()
		}
	}
}