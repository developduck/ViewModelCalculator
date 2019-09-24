package duck.develop.calculator.data.source.remote

import androidx.lifecycle.LiveData
import duck.develop.calculator.data.source.ConfigDataSource
import duck.develop.calculator.data.source.remote.service.ConfigService

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : 앱 환경설정 관련 Remote Source
 */
class ConfigRemoteDataSource(
    private val service: ConfigService
): ConfigDataSource {
    override fun getConfig(): LiveData<Boolean> {
        return service.getConfig()
    }
    override fun getWelcomeToAndroid(): LiveData<String> {
        return service.getWelcomeToAndroid()
    }
    override fun getBaseUrl(): LiveData<String> {
        return service.getBaseUrl()
    }
}