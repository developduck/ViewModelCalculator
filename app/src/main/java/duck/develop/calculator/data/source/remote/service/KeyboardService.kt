package duck.develop.calculator.data.source.remote.service

import androidx.lifecycle.LiveData
import duck.develop.calculator.data.model.Root
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Hwang on 2019-07-10.
 *
 * Description :
 */
interface KeyboardService {
//    @GET("/test/get_keyboard_version.php")
//    fun getKeyboardVersion(@Query("id") id: Int): Single<Root<Keyboard>>
    @GET("/test/get_keyboard.php")
    fun getKeyboardJoinKeyAll(@Query("id") id: Int): LiveData<Root<SelectKeyboardJoinKeyAll>>

}