package ru.pauliesoft.test.ui.livedata_wrapper

import androidx.lifecycle.Observer

class Event<out T>(val content: T) {

    var hasBeenHandled = false
        private set

    fun getIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    @JvmName("content")
    fun peekContent(): T = content

}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}