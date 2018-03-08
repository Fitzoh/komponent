package komponent.core

import org.w3c.dom.HTMLElement

abstract class LoopElement<T> : CustomElement() {
	companion object {
		const val tag = "k-loop"
		fun define() = defineElement<LoopElement<*>>(tag)
	}

	var elements: Collection<T> by observable(emptyList())
	var function by observable<(HTMLElement.(T) -> Unit)?>(null)

	override fun HTMLElement.render() {
		style { textContent = """
			|:host {
			|	display: block;
			|}
		""".trimMargin()
		}

		subscribe(::elements) { doRender() }
		subscribe(::function) { doRender() }
	}

	private fun HTMLElement.doRender() {
		// Do not remove first (<style>) child
		while (firstChild != null && firstChild !== lastChild) {
			removeChild(lastChild!!)
		}

		function?.let { f ->
			elements.forEach { f(it) }
		}
	}
}

fun <T> HTMLElement.loop(elements: Collection<T> = emptyList(), function: HTMLElement.(T) -> Unit): LoopElement<T> {
	return createElement<LoopElement<T>>(LoopElement.tag, this, null).apply {
		this.elements = elements
		this.function = function
	}
}