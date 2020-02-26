package duck.develop.calculator.data.model.entity

import android.os.Parcelable
import androidx.databinding.ObservableField
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hwang on 2019-10-21.
 *
 * Description : 영화 엔티티
 */
@Parcelize data class Movie(
    @Json(name = "id")
    @PrimaryKey
    var id: Int,
    @Json(name = "title")
    var title: ObservableField<String>,
    @Json(name = "order")
    var order: Int
): Parcelable