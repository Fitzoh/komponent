package komponent.event

import komponent.core.Listener
import komponent.core.Subscription
import kotlin.reflect.KClass

object Events {

	private val listeners = hashMapOf<KClass<*>, MutableSet<Listener<*>>>()

	@PublishedApi
	internal fun <T : Any> subscribe(clazz: KClass<T>, listener: Listener<T>): Subscription {
		if (!listeners.containsKey(clazz)) {
			listeners[clazz] = hashSetOf()
		}
		listeners[clazz]!!.add(listener)
		return object : Subscription {
			override fun cancel() {
				val clazzListeners = listeners[clazz]!!
				clazzListeners.remove(listener)
				if (clazzListeners.isEmpty()) {
					listeners.remove(clazz)
				}
			}
		}
	}

	inline fun <reified T : Any> subscribe(noinline listener: Listener<T>) = Events.subscribe(T::class, listener)

	fun <T : Any> emit(event: T) {
		listeners[event::class]?.forEach { listener -> (listener as? Listener<T>)?.invoke(event) }
	}

}