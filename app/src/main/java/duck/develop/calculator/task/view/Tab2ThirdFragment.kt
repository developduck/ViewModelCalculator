package duck.develop.calculator.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import duck.develop.calculator.R
import duck.develop.calculator.task.DefaultFragment

class Tab2ThirdFragment: DefaultFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2_third, container, false).also { v ->
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}