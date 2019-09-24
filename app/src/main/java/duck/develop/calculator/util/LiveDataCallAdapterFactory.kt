package duck.develop.calculator.util

import android.os.Parcelable
import androidx.lifecycle.LiveData
import duck.develop.calculator.data.model.Root
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory<DATA: Parcelable> private constructor()
    : CallAdapter.Factory() {
    companion object {
        @JvmStatic fun create(): LiveDataCallAdapterFactory<Parcelable> {
            return LiveDataCallAdapterFactory()
        }
    }
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<Root<DATA>, LiveData<Root<DATA>>>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)

        require(getRawType(observableType) == Root::class.java) { "type must be a resource" }
        require(observableType is ParameterizedType) { "resource must be parameterized" }

        return LiveDataCallAdapter(observableType)
    }
}