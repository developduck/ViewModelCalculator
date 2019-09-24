package duck.develop.calculator.data.source.local

import duck.develop.calculator.data.source.ConfigDataSource
import duck.develop.calculator.exception.UnimplementedFunctionException

/**
 * Created by Hwang on 2019-08-14.
 *
 * Description : 앱 환경설정 관련 Local Source
 */
class ConfigLocalDataSource: ConfigDataSource {
    override suspend fun getConfig(): Boolean {
        throw UnimplementedFunctionException()
    }
    override suspend fun getWelcomeToAndroid(): String {
        throw UnimplementedFunctionException()
    }
    override suspend fun getBaseUrl(): String {
        throw UnimplementedFunctionException()
    }
}