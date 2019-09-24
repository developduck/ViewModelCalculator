package duck.develop.calculator.util

import android.os.Parcelable
import androidx.lifecycle.LiveData
import duck.develop.calculator.R
import duck.develop.calculator.data.model.Root
import retrofit2.*
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 데이터를 라이브 데이터(LiveData)로 변환하는 아답터
 */
class LiveDataCallAdapter<R: Parcelable>(private val responseType: Type):
    CallAdapter<Root<R>, LiveData<Root<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<Root<R>>): LiveData<Root<R>> {
        return object : LiveData<Root<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<Root<R>> {
                        override fun onResponse(call: Call<Root<R>>, response: Response<Root<R>>) {
                            postValue(response.body())
                        }
                        override fun onFailure(call: Call<Root<R>>, t: Throwable) {
                            if (t is HttpException) {
                                postValue(Root(code = t.code(), message = t.message))
                            } else {
                                postValue(Root(message = t.message))
                            }
                        }
                    })
                }
            }
        }
    }
}