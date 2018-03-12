package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.IronResizableBehavior
import komponent.polymer.behavior.IronSelectableBehavior
import org.w3c.dom.Node

abstract external class IronPagesElement<T> : PolymerElement, IronSelectableBehavior<T>, IronResizableBehavior

fun <T> Node.ironPages(init: (IronPagesElement<T>.() -> Unit)? = null) = createElement("iron-pages", this, init)