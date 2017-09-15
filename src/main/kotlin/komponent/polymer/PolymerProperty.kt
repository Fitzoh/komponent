package komponent.polymer

import komponent.core.Subscription
import komponent.property.MutableProperty
import komponent.property.Property

open class PolymerProperty<out T>(private val delegate: dynamic,
                                  private val property: String) : Property<T> {

    private val listeners = LinkedHashSet<(T) -> Unit>()

    init {
        val dashCasedProperty = property.replace(Regex("[A-Z]"), { "-${it.groupValues[0].toLowerCase()}" })
        delegate.addEventListener("$dashCasedProperty-changed", { event ->
            listeners.forEach { it(event.detail.value as T) }
        })
    }

    override fun get(): T = delegate[property] as T

    override fun subscribe(listener: (T) -> Unit): Subscription {
        listeners.add(listener)
        return object : Subscription {
            override fun cancel() {
                listeners.remove(listener)
            }
        }
    }

}

class PolymerMutableProperty<T>(private val delegate: dynamic,
                                private val property: String) : MutableProperty<T>, PolymerProperty<T>(delegate, property) {

    override fun set(newValue: T) {
        delegate[property] = newValue
    }

}