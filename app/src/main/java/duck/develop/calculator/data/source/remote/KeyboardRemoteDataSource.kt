package duck.develop.calculator.data.source.remote

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.source.remote.service.KeyboardService
import duck.develop.calculator.exception.UnimplementedFunctionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 키보드 관련 Remote Source
 */
class KeyboardRemoteDataSource(
    private val service: KeyboardService
): KeyboardDataSource {
    override suspend fun getKeyboardVersion(id: Int): Long =
        withContext(Dispatchers.IO) {
            return@withContext service.getKeyboardVersion(id).data?.let {
                it.version
            } ?: 0
        }
    override suspend fun getKeyboardJoinKeyAll(id: Int): Result<SelectKeyboardJoinKeyAll> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                service.getKeyboardJoinKeyAll(1).data?.let {
                    Result.Success(it)
                } ?: throw NullPointerException()
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    override suspend fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): Result<SelectKeyboardJoinKeyAll> {
        throw UnimplementedFunctionException()
    }
}