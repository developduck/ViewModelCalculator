package duck.develop.calculator.data.source

import androidx.lifecycle.LiveData
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.model.Result

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 데이터 인터페이스
 */
interface KeyboardDataSource {
    fun getKeyboardJoinKeyAll(id: Int): LiveData<Result<SelectKeyboardJoinKeyAll>>
    fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): LiveData<Result<SelectKeyboardJoinKeyAll>>
}