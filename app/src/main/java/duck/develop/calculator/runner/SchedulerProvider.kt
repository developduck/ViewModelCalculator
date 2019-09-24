package duck.develop.calculator.runner

import io.reactivex.Scheduler

/**
 * Created by Hwang on 2019-07-03.
 *
 * Description : 스케줄러 공급자 인터페이스
 */
interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}