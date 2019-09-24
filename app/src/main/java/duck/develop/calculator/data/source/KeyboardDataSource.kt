package duck.develop.calculator.data.source

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 데이터 인터페이스
 */
interface KeyboardDataSource {
    suspend fun getKeyboardJoinKeyAll(id: Int): Result<SelectKeyboardJoinKeyAll>
    suspend fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): Result<SelectKeyboardJoinKeyAll>
}