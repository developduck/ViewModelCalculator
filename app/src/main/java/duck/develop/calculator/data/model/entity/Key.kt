package duck.develop.calculator.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키 엔티티
 */
@Parcelize @Entity(foreignKeys = [
    //외래키 제약조건
    ForeignKey(
        entity = Keyboard::class,
        parentColumns = ["id"],
        childColumns = ["keyboard_id"],
        onDelete = CASCADE)
])
data class Key(
    @Json(name = "id")
    @PrimaryKey var id: Int,
    @Json(name = "keyboard_id")
    var keyboard_id: Int,
    @Json(name = "display")
    var display: String,
    @Json(name = "command")
    var command: String,
    @Json(name = "order")
    var order: Int,
    @Json(name = "style")
    var style: String? = null,
    @Json(name = "width")
    var width: Int = 0,
    @Json(name = "height")
    var height: Int = 0
): Parcelable