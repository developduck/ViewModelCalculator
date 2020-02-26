package duck.develop.calculator.task

import androidx.lifecycle.*
import duck.develop.calculator.data.model.Result
import duck.develop.core.manager.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import retrofit2.HttpException
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

open class DefaultViewModel: ViewModel() {
    private val _status = MutableLiveData<Int>()
    val status: LiveData<Int> = _status

    fun <T> withErrorHandler(result: Result<T>, success: (T) -> Unit) {
        if (result is Result.Success) {
            success(result.data)
        } else if (result is Result.Error) {
            if (result.exception is HttpException) {
                _status.value = result.exception.code()
            } else {
                Logger.e(result.exception.message, result.exception)
            }
        }
    }
}