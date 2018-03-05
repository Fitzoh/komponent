package komponent.example

import komponent.core.SwitchElement
import komponent.example.element.CounterButton
import komponent.example.element.DummyCard
import komponent.example.element.MyApplication

fun main(args: Array<String>) {
	SwitchElement.define() // TODO do that on komponent, others in example

	CounterButton.define()
	DummyCard.define()
	MyApplication.define()
}
