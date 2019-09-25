package duck.develop.calculator.data.source.local

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.source.local.dao.KeyboardDataAccessObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 관련 Local Source
 */
class KeyboardLocalDataSource(
    private val dao: KeyboardDataAccessObj
): KeyboardDataSource {
    override suspend fun getKeyboardVersion(id: Int): Long =
        withContext(Dispatchers.IO) {
            return@withContext try {
                dao.getKeyboardVersion(id)
            } catch (e: Exception) {
                0L
            }
        }
    override suspend fun getKeyboardJoinKeyAll(id: Int): Result<SelectKeyboardJoinKeyAll> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                dao.getKeyboardJoinKeyAll(id).let {
                    Result.Success(it)
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    override suspend fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): Result<SelectKeyboardJoinKeyAll> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                dao.insert(Keyboard(query), query.keys)
                Result.Success(query)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}