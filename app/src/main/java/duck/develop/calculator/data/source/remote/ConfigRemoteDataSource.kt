package duck.develop.calculator.data.source.remote

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
    override suspend fun getConfig(): Boolean {
        return service.getConfig()
    }
    override suspend fun getWelcomeToAndroid(): String {
        return service.getWelcomeToAndroid()
    }
    override suspend fun getBaseUrl(): String {
        return service.getBaseUrl()
    }
}