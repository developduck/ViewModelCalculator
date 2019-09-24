package duck.develop.calculator.data.event

open class Event<out T>(private val value: T) {
    enum class Type {
        NEXT
    }

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            value
        }
    }

    fun peekContent(): T = value
}