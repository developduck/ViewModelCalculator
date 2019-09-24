package duck.develop.calculator.task

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 공통 처리를 위한 Default View
 */
open class DefaultFragment: Fragment() {
    private val defaultViewModel: DefaultViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultViewModel?.status?.observe(this, Observer {
            Toast.makeText(context, "errorCode:$it", Toast.LENGTH_SHORT).show()
        })
    }
}