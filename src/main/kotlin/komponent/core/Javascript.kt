inline fun <reified T : Any> addStaticMembersTo(source: Any) {
	val c = T::class.js.asDynamic()
	val ownNames = js("Object").getOwnPropertyNames(source) as Array<String>
	val protoNames = js("Object").getOwnPropertyNames(source.asDynamic().constructor.prototype) as Array<String>

	for (name in ownNames + protoNames) {
		c[name] = source.asDynamic()[name]
	}
}