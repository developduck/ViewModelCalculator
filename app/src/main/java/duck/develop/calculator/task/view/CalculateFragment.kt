package duck.develop.calculator.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.view.clicks
import duck.develop.calculator.R
import duck.develop.calculator.data.model.Type
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.databinding.FragmentCalculateBinding
import duck.develop.calculator.extensions.onGlobalLayout
import duck.develop.calculator.task.DefaultFragment
import duck.develop.calculator.task.viewmodel.CalculateViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_calculate.*
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by Hwang on 2019-07-05.
 *
 * Description : Calculate View / 키보드 그리기 및 이벤트 정의
 */
class CalculateFragment: DefaultFragment() {
    private lateinit var viewDataBinding: FragmentCalculateBinding

    private val viewModel by inject<CalculateViewModel>()
    private val disposable = CompositeDisposable()

    private val onClick = { key: Key ->
        when (key.command.toLowerCase(Locale.getDefault())) {
            "ce" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "c" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "delete" -> txt_result.run {
                text = if (text.length == 1) { "0" } else { text.substring(0, text.length - 1) }
            }
            "divide" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "multiply" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "minus" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "plus" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "equal" -> Toast.makeText(activity, key.display, Toast.LENGTH_LONG).show()
            "number" -> txt_result.run {
                text = if (text == "0") { key.display } else { "$text${key.display}" }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculate, container, false).also { v ->
            viewDataBinding = FragmentCalculateBinding.bind(v).apply {
                viewModel = this@CalculateFragment.viewModel
            }
            viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
            viewModel.start(arguments?.getString("INIT_RESULT")!!)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pnl_keyboard.onGlobalLayout {
            viewModel.loadKeyboard(pnl_keyboard.width, pnl_keyboard.height)
        }
        viewModel.keyboard.observe(this, Observer { keyboard ->
            pnl_keyboard.columnCount = keyboard.column_count
        })
        viewModel.keys.observe(this, Observer { keys ->
            keys.forEach { key ->
                getIdentifier("parts_key_${key.style ?: "default"}", "layout") { resId ->
                    pnl_keyboard.addView(LayoutInflater.from(activity).inflate(resId, null).apply {
                        layoutParams = LinearLayout.LayoutParams(key.width, key.height)
                        findViewById<View>(R.id.btn_key).run {
                            when (this) {
                                is TextView -> text = key.display
                                is ImageView -> getIdentifier("selector_btn_key_${key.style}_${key.command}", "drawable") { resId ->
                                    setImageResource(resId)
                                }
                            }
                            isEnabled = key.command.toLowerCase(Locale.getDefault()) != Type.NONE
                            clicks().map { key }.subscribe(onClick).let { disposable.add(it) }
                        }
                    })
                }
            }
        })
    }

    private fun getIdentifier(name: String, defType: String, action: (Int) -> Unit) {
        resources.getIdentifier(name, defType, activity?.packageName)
            .takeIf { it != 0 }
            ?.let { action(it) }
    }
}


























