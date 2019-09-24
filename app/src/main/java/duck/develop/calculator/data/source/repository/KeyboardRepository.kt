package duck.develop.calculator.data.source.repository

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키를 포함하는 키보드 데이터에 대한 저장소(Repository)
 */
class KeyboardRepository(
    private val local: KeyboardDataSource,
    private val remote: KeyboardDataSource
): KeyboardDataSource {
    override suspend fun getKeyboardJoinKeyAll(id: Int): Result<SelectKeyboardJoinKeyAll> {
        return remote.getKeyboardJoinKeyAll(id)
    }
    override suspend fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): Result<SelectKeyboardJoinKeyAll> {
        return local.insertOrUpdateKeyboardWithKeyAll(query)
    }
}