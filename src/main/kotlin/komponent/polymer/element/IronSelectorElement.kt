package komponent.polymer.element

import komponent.core.createElement
import komponent.polymer.behavior.IronMultiSelectableBehavior
import org.w3c.dom.Node

abstract external class IronSelectorElement<T> : PolymerElement, IronMultiSelectableBehavior<T>

fun <T> Node.ironSelector(init: (IronSelectorElement<T>.() -> Unit)? = null) = createElement("iron-selector", this, init)