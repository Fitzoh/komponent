package komponent.core

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node
import org.w3c.dom.asList
import kotlin.dom.addClass
import kotlin.dom.hasClass
import kotlin.dom.removeClass

abstract class SwitchElement<T> : CustomElement() {

	companion object {
		const val tag = "k-switch"
		fun define() = defineElement<SwitchElement<*>>(tag)
		const val hiddenClass = "k-switch-hidden"
	}

	var value by observable<T?>(null)
	var cases by observable<(HTMLElement.(T?) -> Unit)?>(null)
	var lazy by observable(false)

	private var currentChildren = emptyList<Node>()
	private val lazyChildren = hashMapOf<T?, List<Node>>()

	override fun HTMLElement.render() {
		subscribe(::value) { doRender(it, cases) }
		subscribe(::cases) { resetAndRender () }
		subscribe(::lazy) { resetAndRender () }

		style { textContent = """
			|:host {
			|	display: block;
			|}
			|
			|:host > .$hiddenClass {
			|	display: none !important;
			|}
		""".trimMargin()
		}
	}

	private fun HTMLElement.resetAndRender() {
		currentChildren = emptyList()
		lazyChildren.clear()
		removeAllChildren()
		doRender(value, cases)
	}

	private fun HTMLElement.removeAllChildren() {
		// Do not remove first (<style>) child
		while (firstChild != null && firstChild !== lastChild) {
			removeChild(lastChild!!)
		}
	}

	private fun HTMLElement.doRender(value: T?, cases: (HTMLElement.(T?) -> Unit)?) {
		if (lazy) {
			if (currentChildren.isNotEmpty()) {
				currentChildren.forEach { (it as? Element)?.addClass(hiddenClass) }
			}
		} else {
			removeAllChildren()
		}

		cases?.let {
			if (lazy) {
				if (lazyChildren.containsKey(value)) {
					currentChildren = lazyChildren[value]!!
					currentChildren.forEach { (it as? Element)?.removeClass(hiddenClass) }
				} else {
					it(value)
					currentChildren = childNodes.asList().filter { it is Element && !it.hasClass(hiddenClass) }
					lazyChildren[value] = currentChildren
				}
			} else {
				it(value)
			}
		}
	}
}

fun <T> HTMLElement.switch(value: T? = null, lazy: Boolean = false, cases: HTMLElement.(T?) -> Unit): SwitchElement<T> {
	return createElement<SwitchElement<T>>(SwitchElement.tag, this, null).apply {
		this.value = value
		this.cases = cases
		this.lazy = lazy
	}
}

fun HTMLElement.switchIf(value: Boolean? = null, lazy: Boolean = false, action: HTMLElement.() -> Unit): SwitchElement<Boolean> {
	return switch(value, lazy) {
		if (it == true) {
			action()
		}
	}
}