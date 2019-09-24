package duck.develop.calculator.data.source.remote.service

import androidx.lifecycle.LiveData

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description :
 */
interface ConfigService {
    fun getConfig(): LiveData<Boolean>
    fun getWelcomeToAndroid(): LiveData<String>
    fun getBaseUrl(): LiveData<String>
}