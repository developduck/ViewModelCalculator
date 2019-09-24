package duck.develop.calculator.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 엔티티
 */
@Parcelize @Entity data class Keyboard(
    @PrimaryKey var id: Int,
    var column_count: Int = 4,
    var version: Long = 0
): Parcelable {
    constructor(data: SelectKeyboardJoinKeyAll): this(
        data.id,
        data.column_count,
        data.version)
}