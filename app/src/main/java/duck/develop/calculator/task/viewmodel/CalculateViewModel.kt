package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.task.DefaultViewModel
import duck.develop.calculator.data.model.Result
import duck.develop.core.manager.Logger
import kotlinx.coroutines.*
import retrofit2.HttpException

class CalculateViewModel(
    repository: KeyboardDataSource
): DefaultViewModel() {
    private val _keyboard = repository.getKeyboardJoinKeyAll(1).switchMap {
        val result = MutableLiveData<SelectKeyboardJoinKeyAll>()

        if (it is Result.Success) {
            result.value = it.data
            _keys.value = it.data.keys
        } else {
            if (it is Result.Error && it.exception is HttpException) {
                _status.value = it.exception.code()
            }
        }
        return@switchMap result
    }
    val keyboard: LiveData<SelectKeyboardJoinKeyAll> = _keyboard

    private val _keys = MutableLiveData<List<Key>>()
    val keys: LiveData<List<Key>> = _keys

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun start(init: String) {
        _result.value = init
        _loading.value = true
    }

    fun loadKeyboard(width: Int, height: Int) {
        _keyboard.value?.run {
            _keys.value?.withIndex()?.forEach {
                it.value.width = width / column_count
                it.value.height = height / ((keys.size / column_count) + (keys.size % column_count))
            }
            _keys.postValue(_keys.value)
            _loading.value = false
        }
    }
}