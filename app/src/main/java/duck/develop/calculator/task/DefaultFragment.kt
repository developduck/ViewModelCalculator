package duck.develop.calculator.task

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 공통 처리를 위한 Default View
 */
open class DefaultFragment: Fragment() {
    protected var defaultViewModel: DefaultViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultViewModel?.status?.observe(this, Observer<Int> {
            Toast.makeText(context, "errorCode:$it", Toast.LENGTH_SHORT).show()
        })
    }
}