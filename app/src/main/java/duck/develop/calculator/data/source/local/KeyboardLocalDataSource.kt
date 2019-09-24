package duck.develop.calculator.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.source.local.dao.KeyboardDataAccessObj
import duck.develop.calculator.data.model.Result

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 관련 Local Source
 */
class KeyboardLocalDataSource(
    private val dao: KeyboardDataAccessObj
): KeyboardDataSource {
    override fun getKeyboardJoinKeyAll(id: Int): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        return dao.getKeyboardJoinKeyAll(id).map {
            Result.Success(it)
        }
    }
    override fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        return object: LiveData<Result<SelectKeyboardJoinKeyAll>>() {
            override fun onActive() {
                super.onActive()
                postValue(Result.Success(query))
            }
        }
    }
}