package duck.develop.calculator.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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
    @SerializedName(value = "id")
    @PrimaryKey var id: Int,
    @SerializedName(value = "keyboard_id")
    var keyboard_id: Int,
    @SerializedName(value = "display")
    var display: String,
    @SerializedName(value = "command")
    var command: String,
    @SerializedName(value = "order")
    var order: Int,
    @SerializedName(value = "style")
    var style: String? = null,
    @SerializedName(value = "width")
    var width: Int = 0,
    @SerializedName(value = "height")
    var height: Int = 0
): Parcelable