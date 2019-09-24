package duck.develop.calculator.exception.handler

import io.reactivex.functions.Consumer
import retrofit2.HttpException

/**
 * Created by Hwang on 2019-07-26.
 *
 * Description : 오류 공통처리 클래스
 */
class ErrorHandler(
    val action: (error: String) -> Unit
): Consumer<Throwable> {
    override fun accept(t: Throwable) {
        if (t is HttpException) {
            action("Code:${t.code()}:Message:${t.message()}")
        } else {
            action("${t.message}")
        }
    }
}

/**
 * 오류를 공통처리 하기 위한 핸들러 함수
 */
fun errorHandler(
    action: (error: String) -> Unit
): ((it: Throwable) -> Unit) {
    return {
        if (it is HttpException) {
            action("Code:${it.code()}:Message:${it.message()}")
        } else {
            action("${it.message}")
        }
    }
}