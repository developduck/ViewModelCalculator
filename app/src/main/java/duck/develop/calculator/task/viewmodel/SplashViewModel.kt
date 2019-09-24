package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import duck.develop.calculator.data.source.repository.ConfigRepository
import duck.develop.calculator.extensions.zip
import duck.develop.calculator.runner.SchedulerProvider
import duck.develop.calculator.task.DefaultViewModel
import duck.develop.calculator.data.event.Event
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class SplashViewModel(
    repository: ConfigRepository,
    private val scheduler: SchedulerProvider
) : DefaultViewModel() {
    private lateinit var disposable: CompositeDisposable

    private val _event = MutableLiveData<Event<Event.Type>>()
    val event: LiveData<Event<Event.Type>> = _event

    private val _message = repository.getWelcomeToAndroid()
    val message: LiveData<String> = _message

    private val _config = repository.getConfig()
    val config: LiveData<Boolean> = _config

    fun start() {
        disposable = CompositeDisposable()
    }

    fun initialize() {
        ArrayList<Single<out Any>>().run {
            //Splash 최소 시간 설정
            add(Single.timer(1500, TimeUnit.MILLISECONDS))
            return@run zip { Unit }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(Consumer {
                    _event.value = Event(Event.Type.NEXT)
                })
        }.let {
            disposable.add(it)
        }
    }
}