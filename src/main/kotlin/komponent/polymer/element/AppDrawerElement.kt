package komponent.polymer.element

import komponent.core.createElement
import org.w3c.dom.HTMLElement

external abstract class AppDrawerElement : PolymerElement

fun HTMLElement.appDrawer(init: (AppDrawerElement.() -> Unit)? = null) = createElement("app-drawer", this, init)