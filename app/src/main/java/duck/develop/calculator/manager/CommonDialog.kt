package duck.develop.calculator.manager

import android.content.Context
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Created by Hwang on 2019-08-16.
 *
 * Description : 공통 다이얼로그 (Wrapper Class)
 */
class CommonDialog private constructor(private val builder: Builder) {
    companion object {
        fun with(context: Context, @StringRes res: Int): Builder {
            return with(context, message = context.getString(res))
        }
        fun with(context: Context, message: String): Builder {
            return Builder(context, message = message)
        }
    }
    class Builder(
        var context: Context,
        var message: String,
        var title: String? = null,
        var positiveText: String? = null,
        var onPositive: (() -> Unit)? = null,
        var negativeText: String? = null,
        var onNegative: (() -> Unit)? = null
    ) {
        fun build() = CommonDialog(this)
    }
    fun showOK() {
        builder.run {
            positiveText = "OK"
            show()
        }
    }
    fun showRetryOrCancel() {
        builder.run {
            positiveText = "Retry"
            negativeText = "Cancel"
            show()
        }
    }
    fun show() {
        MaterialDialog(builder.context).show {
            builder.run {
                title?.let { title(text = it) }
                message(text = message)
                positiveText?.let {
                    positiveButton { onPositive?.invoke() }
                }
                negativeText?.let {
                    negativeButton { onNegative?.invoke() }
                }
            }
        }
    }
}