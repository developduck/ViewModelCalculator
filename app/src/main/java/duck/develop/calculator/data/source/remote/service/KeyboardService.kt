package duck.develop.calculator.data.source.remote.service

import duck.develop.calculator.data.model.Root
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Hwang on 2019-07-10.
 *
 * Description : Keyboard Service 추상 객체
 */
interface KeyboardService {
//    @GET("/test/get_keyboard_version.php")
//    fun getKeyboardVersion(@Query("id") id: Int): Single<Root<Keyboard>>
    @GET("/test/get_keyboard.php")
    suspend fun getKeyboardJoinKeyAll(@Query("id") id: Int): Root<SelectKeyboardJoinKeyAll>
}