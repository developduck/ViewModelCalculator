package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import duck.develop.calculator.data.source.repository.ConfigRepository
import duck.develop.calculator.task.DefaultViewModel
import duck.develop.calculator.data.event.Event
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class SplashViewModel(
    private val repository: ConfigRepository
) : DefaultViewModel() {
    private lateinit var disposable: CompositeDisposable

    private val _event = MutableLiveData<Event<Event.Type>>()
    val event: LiveData<Event<Event.Type>> = _event

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _config = MutableLiveData<Boolean>()
    val config: LiveData<Boolean> = _config

    fun start() {
        disposable = CompositeDisposable()
        viewModelScope.launch {
            _config.value = repository.getConfig()
            _message.value = repository.getWelcomeToAndroid()
        }
    }

    fun initialize() {
        viewModelScope.launch {
            listOf(
                //Splash 최소 시간 설정
                async { delay(1500) }
            ).awaitAll()
            _event.value = Event(Event.Type.NEXT)
        }
    }
}