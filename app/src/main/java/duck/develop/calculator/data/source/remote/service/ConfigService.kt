package duck.develop.calculator.data.source.remote.service

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : Config Service 추상 객체
 */
interface ConfigService {
    suspend fun getConfig(): Boolean
    suspend fun getWelcomeToAndroid(): String
    suspend fun getBaseUrl(): String
}