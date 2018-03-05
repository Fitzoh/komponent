package komponent.core

import org.w3c.dom.HTMLElement

abstract class SwitchElement<T> : CustomElement() {

	companion object {
		const val tag = "k-switch"
		fun define() = defineElement<SwitchElement<*>>(tag)
	}

	var value by property<T?>(null)
	var cases by property<(HTMLElement.(T?) -> Unit)?>(null)

	override fun HTMLElement.render() {
		subscribe(::value) { doRender(it, cases) }
		subscribe(::cases) { doRender(value, it) }
	}

	private fun HTMLElement.doRender(value: T?, cases: (HTMLElement.(T?) -> Unit)?) {
		while (hasChildNodes()) {
			removeChild(lastChild!!)
		}
		cases?.let { it(value) }
	}
}

fun <T> HTMLElement.switch(value: T? = null, cases: HTMLElement.(T?) -> Unit): SwitchElement<T> {
	return createElement<SwitchElement<T>>(SwitchElement.tag, this, null).apply {
		this.value = value
		this.cases = cases
	}
}

fun HTMLElement.switchIf(value: Boolean? = null, action: HTMLElement.() -> Unit): SwitchElement<Boolean> {
	return switch(value) {
		if (it == true) {
			action()
		}
	}
}