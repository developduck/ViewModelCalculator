package duck.develop.calculator.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 화면 단위가 Fragment가 되면서 단일 Activity를 사용하기 때문에 필요없을 것 같지만 혹시 몰라서 남겨둠
 */
fun AppCompatActivity.setupActionBarWithNavController(
    navController: NavController,
    vararg topLevelDestinationIds: Int) {
    NavigationUI.setupActionBarWithNavController(this, navController, AppBarConfiguration(topLevelDestinationIds.toSet()))
}
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes resId: Int) {
    supportFragmentManager.transact {
        replace(resId, fragment)
    }
}
fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commit()
}

@SuppressLint("Registered")
open class DefaultActivity: AppCompatActivity()