package duck.develop.calculator.data.source.local

import androidx.lifecycle.LiveData
import duck.develop.calculator.data.source.ConfigDataSource
import duck.develop.calculator.exception.UnimplementedFunctionException
import io.reactivex.Completable

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : 앱 환경설정 관련 Local Source
 */
class ConfigLocalDataSource: ConfigDataSource {
    override fun getConfig(): LiveData<Boolean> {
        throw UnimplementedFunctionException()
    }
    override fun getWelcomeToAndroid(): LiveData<String> {
        throw UnimplementedFunctionException()
    }
    override fun getBaseUrl(): LiveData<String> {
        throw UnimplementedFunctionException()
    }
}