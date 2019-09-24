package duck.develop.calculator.task

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

open class DefaultViewModel: ViewModel() {
    protected val _status = MutableLiveData<Int>()
    val status: LiveData<Int> = _status
}