package duck.develop.calculator.task.container

import android.os.Bundle
import android.view.View
import duck.develop.calculator.R
import duck.develop.calculator.task.DefaultActivity
import duck.develop.calculator.task.replaceFragmentInActivity
import duck.develop.calculator.task.view.SplashFragment

class SplashActivity: DefaultActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportFragmentManager.findFragmentById(R.id.content) as SplashFragment?
            ?: SplashFragment().also {
                replaceFragmentInActivity(it, R.id.content)
            }
    }
}