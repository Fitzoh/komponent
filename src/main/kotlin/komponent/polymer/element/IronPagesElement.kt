package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.IronResizableBehavior
import komponent.polymer.behavior.IronSelectableBehavior
import org.w3c.dom.HTMLElement

external abstract class IronPagesElement<T> : PolymerElement, IronSelectableBehavior<T>, IronResizableBehavior

fun <T> HTMLElement.ironPages(init: (IronPagesElement<T>.() -> Unit)? = null) = createElement("iron-pages", this, init)