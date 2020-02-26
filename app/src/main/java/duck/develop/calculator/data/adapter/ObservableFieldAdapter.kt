package duck.develop.calculator.data.adapter

import androidx.databinding.ObservableField
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ObservableFieldStringAdapter {
    @ToJson
    fun toJson(field: ObservableField<String>) = field.get()
    @FromJson
    fun fromJson(data: String) = ObservableField<String>(data)
}