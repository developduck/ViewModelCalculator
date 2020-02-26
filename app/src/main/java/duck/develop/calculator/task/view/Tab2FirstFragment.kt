package duck.develop.calculator.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import duck.develop.calculator.R
import duck.develop.calculator.task.DefaultFragment
import kotlinx.android.synthetic.main.fragment_tab2_first.*

class Tab2FirstFragment: DefaultFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2_first, container, false).also { v ->
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_next.setOnClickListener { findNavController().navigate(R.id.action_tab2_first_fragment_to_tab2_second_fragment) }
    }
}