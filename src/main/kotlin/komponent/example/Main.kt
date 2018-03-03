package komponent.example

import komponent.example.element.CounterButton
import komponent.example.element.DummyCard
import komponent.example.element.MyApplication

fun main(args: Array<String>) {
	CounterButton.define()
	DummyCard.define()
	MyApplication.define()
}
