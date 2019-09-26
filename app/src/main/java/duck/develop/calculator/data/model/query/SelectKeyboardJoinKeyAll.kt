package duck.develop.calculator.data.model.query;

import android.os.Parcelable
import androidx.room.Relation
import com.squareup.moshi.Json
import duck.develop.calculator.data.model.entity.Key
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hwang on 2019-07-03.
 *
 * Description : 키보드 엔티티와 해당 키보드를 참조하고 있는 키 엔티티를 조회하는 데이터 클래스
 */
@Parcelize data class SelectKeyboardJoinKeyAll(
        @Json(name = "id")
        var id: Int,
        @Json(name = "column_count")
        var column_count: Int = 4,
        @Json(name = "version")
        var version: Long = 0,
        @Json(name = "keys")
        @Relation(parentColumn = "id", entityColumn = "keyboard_id")
        var keys: List<Key> = ArrayList()
): Parcelable
