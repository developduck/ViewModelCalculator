package duck.develop.calculator.data.source

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : 앱 환경설정 데이터 인터페이스
 */
interface ConfigDataSource {
    suspend fun getConfig(): Boolean
    suspend fun getWelcomeToAndroid(): String
    suspend fun getBaseUrl(): String
}