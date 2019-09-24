package duck.develop.calculator.data.source.repository

import androidx.lifecycle.LiveData
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.model.Result

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키를 포함하는 키보드 데이터에 대한 저장소(Repository)
 */
class KeyboardRepository(
    private val local: KeyboardDataSource,
    private val remote: KeyboardDataSource
): KeyboardDataSource {
    override fun getKeyboardJoinKeyAll(id: Int): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        return remote.getKeyboardJoinKeyAll(id)
    }
    override fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        return local.insertOrUpdateKeyboardWithKeyAll(query)
    }
}