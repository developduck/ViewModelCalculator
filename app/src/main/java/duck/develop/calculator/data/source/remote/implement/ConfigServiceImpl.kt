package duck.develop.calculator.data.source.remote.implement

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import duck.develop.calculator.BuildConfig
import duck.develop.calculator.data.model.Const
import duck.develop.calculator.data.source.remote.service.ConfigService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : ConfigService 구현
 */
class ConfigServiceImpl(
    private val config: FirebaseRemoteConfig
): ConfigService {
    override suspend fun getConfig(): Boolean = suspendCoroutine {
        config.apply {
            setConfigSettingsAsync(
                FirebaseRemoteConfigSettings
                    .Builder()
                    .setFetchTimeoutInSeconds(30L)
                    .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0L else (5 * 60).toLong())
                    .build()
            )
            fetchAndActivate().addOnCompleteListener { task ->
                it.resume(task.isSuccessful)
            }
        }
    }
    override suspend fun getWelcomeToAndroid(): String {
        return config.getString(getKey(Const.WELCOME_TO_ANDROID))
    }
    override suspend fun getBaseUrl(): String {
        return config.getString(getKey(Const.BASE_URL))
    }
    private fun getKey(key: String): String {
        return if (BuildConfig.FLAVOR != "calculator") { "${BuildConfig.FLAVOR}_$key" } else { key }
    }
}