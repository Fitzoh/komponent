package komponent.polymer.element

external abstract class PaperToastElement : PolymerElement {

	fun show(properties: ToastProperties)

	fun hide()

}

class ToastProperties(val text: String,
					  val duration: Long)
