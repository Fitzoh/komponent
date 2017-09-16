package komponent.polymer

import komponent.core.Element
import komponent.core.createElement
import komponent.core.insert
import komponent.property.Property
import org.w3c.dom.HTMLElement

interface AppDrawerLayout : Element {

	val drawer: Drawer

	var forceNarrow: Boolean

	val narrow: Property<Boolean>

	var openedWhenNarrow: Boolean

	var responsiveWidth: String

	interface Drawer : Element

}

private class AppDrawerLayoutImpl : AppDrawerLayout {

	private val delegate = createElement<HTMLElement>("app-drawer-layout")
	override val drawer = DrawerImpl()

	override var forceNarrow by PolymerVariable<Boolean>(delegate, "forceNarrow")
	override val narrow by lazy { PolymerProperty<Boolean>(delegate, "narrow") }
	override var openedWhenNarrow by PolymerVariable<Boolean>(delegate, "openedWhenNarrow")
	override var responsiveWidth by PolymerVariable<String>(delegate, "responsiveWidth")

	override fun asHtmlElement(): HTMLElement = delegate

	private inner class DrawerImpl : AppDrawerLayout.Drawer {

		private val delegate = createElement<HTMLElement>("app-drawer", this@AppDrawerLayoutImpl.delegate).apply { slot = "drawer" }

		override fun asHtmlElement(): HTMLElement = delegate

	}

}

fun HTMLElement.drawerLayout(init: (AppDrawerLayout.() -> Unit)? = null): AppDrawerLayout = AppDrawerLayoutImpl().also { init?.invoke(it) ; this.insert(it) }
fun Element.drawerLayout(init: (AppDrawerLayout.() -> Unit)? = null): AppDrawerLayout = AppDrawerLayoutImpl().also { init?.invoke(it) ; this.insert(it) }

fun AppDrawerLayout.drawer(init: (AppDrawerLayout.Drawer.() -> Unit)? = null): AppDrawerLayout.Drawer = drawer.also { init?.invoke(it) }