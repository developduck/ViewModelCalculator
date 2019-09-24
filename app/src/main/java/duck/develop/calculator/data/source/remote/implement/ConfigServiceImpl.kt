package duck.develop.calculator.data.source.remote.implement

import androidx.lifecycle.LiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import duck.develop.calculator.BuildConfig
import duck.develop.calculator.data.model.Const
import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.source.remote.service.ConfigService
import io.reactivex.Completable

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : ConfigService 구현
 */
class ConfigServiceImpl(
    private val config: FirebaseRemoteConfig
): ConfigService {
    override fun getConfig(): LiveData<Boolean> {
        return object: LiveData<Boolean>() {
            override fun onActive() {
                super.onActive()
                config.apply {
                    setConfigSettingsAsync(
                        FirebaseRemoteConfigSettings
                            .Builder()
                            .setFetchTimeoutInSeconds(30L)
                            .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0L else (5 * 60).toLong())
                            .build()
                    )
                    fetchAndActivate().addOnCompleteListener { task ->
                        postValue(task.isSuccessful)
                    }
                }
            }
        }
    }
    override fun getWelcomeToAndroid(): LiveData<String> {
        return object: LiveData<String>() {
            override fun onActive() {
                super.onActive()
                postValue(config.getString(getKey(Const.WELCOME_TO_ANDROID)))
            }
        }
    }
    override fun getBaseUrl(): LiveData<String> {
        return object: LiveData<String>() {
            override fun onActive() {
                super.onActive()
                postValue(config.getString(getKey(Const.BASE_URL)))
            }
        }
    }
    private fun getKey(key: String): String {
        return if (BuildConfig.FLAVOR != "calculator") { "${BuildConfig.FLAVOR}_$key" } else { key }
    }
}