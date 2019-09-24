package duck.develop.calculator.data.model

/**
 * Created by Hwang on 2019-09-24.
 *
 * Description : 결과의 상태 값을 가지는 sealed class
 *               참조 - https://kotlinlang.org/docs/reference/sealed-classes.html
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}