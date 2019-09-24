package duck.develop.calculator.data.source.repository

import androidx.lifecycle.LiveData
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
    override fun getConfig(): LiveData<Boolean> {
        return remote.getConfig()
    }
    override fun getWelcomeToAndroid(): LiveData<String> {
        return remote.getWelcomeToAndroid()
    }
    override fun getBaseUrl(): LiveData<String> {
        return remote.getBaseUrl()
    }
}