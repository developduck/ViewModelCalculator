package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.task.DefaultViewModel
import duck.develop.calculator.data.model.Result
import duck.develop.calculator.di.module.repository
import duck.develop.core.manager.Logger
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.http.HTTP

class CalculateViewModel(
    private val repository: KeyboardDataSource
): DefaultViewModel() {
    private val _keyboard = MutableLiveData<SelectKeyboardJoinKeyAll>()
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
        viewModelScope.launch {
            repository.getKeyboardJoinKeyAll(1).let { result ->
                if (result is Result.Success) {
                    _keyboard.value = result.data.apply {
                        result.data.keys.withIndex().forEach {
                            it.value.width = width / column_count
                            it.value.height = height / ((result.data.keys.size / column_count) + (result.data.keys.size % column_count))
                        }
                    }
                    _keys.value = result.data.keys
                    _loading.value = false
                } else {
                    if (result is Result.Error && result.exception is HttpException) {
                        _status.value = result.exception.code()
                    }
                }
            }
        }
    }
}