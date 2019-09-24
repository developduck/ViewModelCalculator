package duck.develop.calculator.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import duck.develop.calculator.R
import duck.develop.calculator.databinding.FragmentSplashBinding
import duck.develop.calculator.manager.CommonDialog
import duck.develop.calculator.task.DefaultFragment
import duck.develop.calculator.data.event.Event
import duck.develop.calculator.data.event.EventObserver
import duck.develop.calculator.task.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject

/**
 * Created by Hwang on 2019-07-05.
 *
 * Description : Splash View / 로고 출력
 */
class SplashFragment: DefaultFragment() {
    private lateinit var viewDataBinding: FragmentSplashBinding

    private val viewModel by inject<SplashViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvent()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false).also {
            viewDataBinding = FragmentSplashBinding.bind(it).apply {
                viewModel = this@SplashFragment.viewModel
            }
            viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
            viewModel.start()
            viewModel.config.observe(this, Observer { isSuccessful ->
                if (isSuccessful) {
                    viewModel.initialize()
                } else {
                    activity?.let { activity ->
                        CommonDialog.with(activity, R.string.guide_failed_to_initialize_app).run {
                            onPositive = { activity.finish() }
                            build()
                        }.showOK()
                    }
                }
            })
            viewModel.message.observe(this, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setupEvent() {
        viewModel.event.observe(this, EventObserver { type ->
            when (type) {
                Event.Type.NEXT -> findNavController().navigate(
                    R.id.action_splash_fragment_to_calculate_fragment,
                    Bundle().apply {
                        putString("INIT_RESULT", "0")
                    })
            }
        })
    }
}