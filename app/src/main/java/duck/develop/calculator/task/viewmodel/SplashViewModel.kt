package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import duck.develop.calculator.data.event.Event
import duck.develop.calculator.data.source.repository.ConfigRepository
import duck.develop.calculator.task.DefaultViewModel
import kotlinx.coroutines.*

class SplashViewModel(
    private val repository: ConfigRepository
) : DefaultViewModel() {
    private val _navigate = MutableLiveData<Event<Unit>>()
    val navigate: LiveData<Event<Unit>> = _navigate

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _config = MutableLiveData<Boolean>()
    val config: LiveData<Boolean> = _config

    fun start() = viewModelScope.launch {
        _config.value = repository.getConfig()
        _message.value = repository.getWelcomeToAndroid()
    }
    @FlowPreview
    fun initialize() = viewModelScope.launch {
        listOf(
            //Splash 최소 시간 설정
            async { delay(1500) }
        ).awaitAll()
        _navigate.value = Event(Unit)
    }
}