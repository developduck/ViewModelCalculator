package duck.develop.calculator.data.source.repository

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키를 포함하는 키보드 데이터에 대한 저장소(Repository)
 */
class KeyboardRepository(
    private val local: KeyboardDataSource,
    private val remote: KeyboardDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): KeyboardDataSource {
    override suspend fun getKeyboardVersion(id: Int): Long {
        return remote.getKeyboardVersion(id)
    }
    override suspend fun getKeyboardJoinKeyAll(id: Int): Result<SelectKeyboardJoinKeyAll> =
        withContext(dispatcher) {
            return@withContext if (local.getKeyboardVersion(id) < remote.getKeyboardVersion(id)) {
                remote.getKeyboardJoinKeyAll(id).let {
                    when (it) {
                        is Result.Success -> insertOrUpdateKeyboardWithKeyAll(it.data)
                        is Result.Error -> it
                    }
                }
            } else {
                local.getKeyboardJoinKeyAll(id)
            }
        }
    override suspend fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): Result<SelectKeyboardJoinKeyAll> {
        return local.insertOrUpdateKeyboardWithKeyAll(query)
    }
}