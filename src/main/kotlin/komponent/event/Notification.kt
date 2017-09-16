package komponent.event

data class Notification(val message: String,
						val duration: Long? = 3_000) {

	fun send() {
		Events.emit(this)
	}

}