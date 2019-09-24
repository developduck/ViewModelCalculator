package duck.develop.calculator.extensions

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Hwang on 2019-07-05.
 *
 * Description : ReactiveX 관련 Extensions
 */
fun <T, R> List<Observable<out T>>.combineLatest(action: (Array<Any>) -> R): Observable<R> =
    Observable.combineLatest<T, R>(this, action)
fun <T, R> List<Single<out T>>.zip(zipper: (Array<Any>) -> R): Single<R> =
    Single.zip(this, zipper)