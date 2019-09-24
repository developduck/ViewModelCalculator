package duck.develop.calculator.extensions

import android.view.View
import android.view.ViewTreeObserver

/**
 * Created by Hwang on 2019-07-05.
 *
 * Description : View 관련 Extensions
 */
fun View.onGlobalLayout(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            action()
        }
    })
}