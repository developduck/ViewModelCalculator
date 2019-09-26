package duck.develop.calculator.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.squareup.moshi.Json
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 엔티티
 */
@Parcelize @Entity data class Keyboard(
    @Json(name = "id")
    @PrimaryKey
    var id: Int,
    @Json(name = "column_count")
    var column_count: Int = 4,
    @Json(name = "version")
    var version: Long = 0
): Parcelable {
    constructor(data: SelectKeyboardJoinKeyAll): this(
        data.id,
        data.column_count,
        data.version)
}