package duck.develop.calculator.task.container

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import duck.develop.calculator.R
import duck.develop.calculator.task.DefaultActivity
import duck.develop.calculator.task.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity: DefaultActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setSupportActionBar(toolbar)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        Navigation.findNavController(this, R.id.container).let {
            setupActionBarWithNavController(it, R.id.calculate_fragment,
                R.id.tab2_first_fragment, R.id.movies_fragment)
            nav_bottom.setupWithNavController(it)

            it.addOnDestinationChangedListener { _, destination, _ ->
                if(destination.id == R.id.tab2_third_fragment) {
                    pnl_app_bar.visibility = View.GONE
                    nav_bottom.visibility = View.GONE
                } else {
                    pnl_app_bar.visibility = View.VISIBLE
                    nav_bottom.visibility = View.VISIBLE
                }
            }
        }
    }
}