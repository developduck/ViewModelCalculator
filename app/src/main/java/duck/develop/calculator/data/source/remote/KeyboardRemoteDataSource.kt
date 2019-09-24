package duck.develop.calculator.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.source.remote.service.KeyboardService
import duck.develop.calculator.exception.UnimplementedFunctionException
import duck.develop.calculator.data.model.Result

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 키보드 관련 Remote Source
 */
class KeyboardRemoteDataSource(
    private val service: KeyboardService
): KeyboardDataSource {
    override fun getKeyboardJoinKeyAll(id: Int): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        return service.getKeyboardJoinKeyAll(1).map {
            it.data
                ?.let { data ->
                    Result.Success(data)
                }
                ?: Result.Error(Exception("data is null"))

        }
    }
    override fun insertOrUpdateKeyboardWithKeyAll(query: SelectKeyboardJoinKeyAll): LiveData<Result<SelectKeyboardJoinKeyAll>> {
        throw UnimplementedFunctionException()
    }
}