package duck.develop.calculator.data.source.repository

import duck.develop.calculator.data.source.ConfigDataSource

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : 앱 환경설정 데이터에 대한 저장소(Repository)
 */
class ConfigRepository(
    private val local: ConfigDataSource,
    private val remote: ConfigDataSource
): ConfigDataSource {
    override suspend fun getConfig(): Boolean {
        return remote.getConfig()
    }
    override suspend fun getWelcomeToAndroid(): String {
        return remote.getWelcomeToAndroid()
    }
    override suspend fun getBaseUrl(): String {
        return remote.getBaseUrl()
    }
}