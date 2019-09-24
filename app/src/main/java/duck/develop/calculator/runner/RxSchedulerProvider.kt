package duck.develop.calculator.runner

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hwang on 2019-07-04.
 *
 * Description : ReactiveX 스케줄러 공급자
 */
class RxSchedulerProvider: SchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }
    override fun io(): Scheduler {
        return Schedulers.io()
    }
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}