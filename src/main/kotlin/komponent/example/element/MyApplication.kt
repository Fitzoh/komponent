package komponent.example.element

import komponent.core.CustomElement
import komponent.core.createElement
import komponent.core.defineElement
import komponent.core.div
import komponent.core.on
import komponent.core.style
import komponent.core.text
import komponent.example.Icons
import komponent.example.Styles
import komponent.polymer.behavior.onSelectedChanged
import komponent.polymer.element.AppDrawerLayoutElement
import komponent.polymer.element.IronSelectorElement
import komponent.polymer.element.PaperIconButtonElement
import komponent.polymer.element.appDrawerLayout
import komponent.polymer.element.appHeaderLayout
import komponent.polymer.element.appToolbar
import komponent.polymer.element.customStyle
import komponent.polymer.element.drawer
import komponent.polymer.element.header
import komponent.polymer.element.icon
import komponent.polymer.element.ironPages
import komponent.polymer.element.ironSelector
import komponent.polymer.element.mainTitle
import komponent.polymer.element.paperIconButton
import komponent.polymer.element.paperIconItem
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement

data class Page(val icon: String,
				val title: String,
				val number: Int)

abstract class MyApplication : CustomElement() {
	companion object {
		const val tag = "my-app"
		fun define() = defineElement<MyApplication>(tag)
	}

	private lateinit var menuButton: PaperIconButtonElement
	private lateinit var drawerLayout: AppDrawerLayoutElement
	private lateinit var tabSelector: IronSelectorElement<HTMLDivElement>

	override fun HTMLElement.render() {
		val pages = listOf(
				Page(Icons.one, "First view", 1),
				Page(Icons.two, "Second view", 2),
				Page(Icons.three, "Last view", 3)
		)

		// Setting polymer CSS properties and mixins
		customStyle {
			style {
				textContent = """
						|:host {
						|   --app-drawer-content-container: {
						|   	background-color: #eee;
						|   };
						|	--app-drawer-layout-content-transition: margin 0.2s;
						|	--paper-font-common-base: {
						|		font-family: 'Lato', 'Roboto', 'Noto', sans-serif;
						|	};
						|	--paper-font-subhead: {
						|		font-family: 'Lato', 'Roboto', 'Noto', sans-serif;
						|	};
						|
						|	--paper-button: {
						|		font-size: 14px;
						|		text-transform: none;
						|		border-radius: 4px;
						|		height: 36px;
						|		padding: 0 24px;
						|		margin: 0;
						|	}
						|}
						|
						|paper-icon-item {
						|	--paper-item-selected: {
						|		background-color: #dadada;
						|	};
						|}
						|
						|paper-icon-item:hover {
						|	background-color: #dadada;
						|}
						|
						|paper-icon-item:focus:before {
						|	background: none;
						|}
					""".trimMargin()
			}
		}

		appHeaderLayout {
			header {
				Styles.primaryBackground(this)
				fixed = true
				shadow = true
				appToolbar {
					menuButton = paperIconButton { icon = Icons.menu }
					mainTitle = div { textContent = "Komponent" }
				}
			}

			drawerLayout = appDrawerLayout {
				drawer {
					div {
						style.apply {
							marginTop = "80px"
							marginLeft = "16px"
						}
						tabSelector = ironSelector {
							pages.forEach { page ->
								paperIconItem {
									style.cursor = "pointer"
									icon = page.icon
									text(page.title)
								}
							}
						}
					}
				}

				val ironPages = ironPages<DummyCard> {
					pages.forEach {
						dummyCard { heading = it.title; number = it.number }
					}
				}
				tabSelector.onSelectedChanged = { ironPages.selected = it }
			}
		}

		// Open first tab
		tabSelector.selected = "0"

		// Handle drawer layout menu button
		menuButton.on("click") {
			if (drawerLayout.forceNarrow || !drawerLayout.narrow) {
				drawerLayout.forceNarrow = !drawerLayout.forceNarrow
			} else {
				drawerLayout.drawer.toggle()
			}
		}
	}
}

fun HTMLElement.myApp(init: (MyApplication.() -> Unit)? = null) = createElement(MyApplication.tag, this, init)