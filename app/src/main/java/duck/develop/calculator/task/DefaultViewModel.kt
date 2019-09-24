package duck.develop.calculator.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DefaultViewModel: ViewModel() {
    protected val _status = MutableLiveData<Int>()
    val status: LiveData<Int> = _status
}