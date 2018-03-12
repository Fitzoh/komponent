package komponent.core

import org.w3c.dom.HTMLElement
import org.w3c.dom.Node

abstract class SwitchElement<T> : CustomElement(false) {

	companion object {
		const val tag = "k-switch"
		fun define() = defineElement<SwitchElement<*>>(tag)
	}

	var value by observable<T?>(null)
	var cases by observable<(Node.(T?) -> Unit)?>(null)
	var lazy by observable(false)

	private var currentChildren = arrayListOf<Node>()
	private val lazyChildren = hashMapOf<T?, ArrayList<Pair<Node, String?>>>()

	private val appendChildForwarder = appendChildForwarder(::doAppendChild)

	override fun Node.render() {
		subscribe(::value) { doRender() }
		subscribe(::cases) { resetAndRender() }
		subscribe(::lazy) { resetAndRender() }
	}

	private fun Node.resetAndRender() {
		lazyChildren.clear()
		removeAllChildren()
		doRender()
	}

	private fun Node.removeAllChildren() {
		var i = currentChildren.size - 1
		while (i >= 0) {
			parentNode!!.removeChild(currentChildren[i--])
		}
		currentChildren.clear()
	}

	private fun Node.doRender() {
		// Remove or hide current children
		if (lazy) {
			currentChildren.forEach { (it as? HTMLElement)?.style?.display = "none" }
		} else {
			removeAllChildren()
		}

		// Add (back) children for the current value
		cases?.let { function ->
			if (lazy) {
				if (lazyChildren.containsKey(value)) {
					val childrenWithDisplay = lazyChildren[value]!!
					currentChildren = childrenWithDisplay.mapTo(arrayListOf()) { it.first }
					childrenWithDisplay.forEach { (node, display) ->
						(node as? HTMLElement)?.let {
							it.style.display = display!!
						}
					}
				} else {
					appendChildForwarder.function(value)
					lazyChildren[value] = currentChildren.mapTo(arrayListOf(), {
						Pair(it, (it as? HTMLElement)?.style?.display)
					})
				}
			} else {
				appendChildForwarder.function(value)
			}
		}
	}

	private fun doAppendChild(child: Node): Node {
		val node = parentNode!!.insertBefore(child, this)
		currentChildren.add(node)
		return node
	}
}

fun <T> Node.switch(value: T? = null, lazy: Boolean = false, cases: Node.(T?) -> Unit): SwitchElement<T> {
	return createElement<SwitchElement<T>>(SwitchElement.tag, this, null).apply {
		this.lazy = lazy
		this.value = value
		this.cases = cases
	}
}

fun Node.switchIf(value: Boolean? = null, lazy: Boolean = false, action: Node.() -> Unit): SwitchElement<Boolean> {
	return switch(value, lazy) {
		if (it == true) {
			action()
		}
	}
}