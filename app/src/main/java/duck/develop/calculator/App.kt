package duck.develop.calculator

import android.app.Activity
import android.os.Bundle
import com.facebook.stetho.Stetho
import duck.develop.calculator.di.module.*
import duck.develop.core.AppCore
import duck.develop.core.manager.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.MESSAGE

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description :
 */
class App: AppCore() {
    override fun onCreate() {
        super.onCreate()
        //디버깅 초기화
        Stetho.initializeWithDefaults(this)

        //의존성 주입 초기화
        startKoin {
            androidContext(this@App.applicationContext)
            logger(object: org.koin.core.logger.Logger() {
                override fun log(level: Level, msg: MESSAGE) {
                    when (level) {
                        Level.DEBUG -> Logger.d(msg)
                        Level.INFO -> Logger.i(msg)
                        Level.ERROR -> Logger.e(msg)
                    }
                }
            })
            androidLogger()
            modules(listOf(
                common, repository, database,
                network, viewModel
            ))
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        Logger.d("")
    }
    override fun onActivityResumed(activity: Activity) {
        super.onActivityResumed(activity)
        Logger.d("")
    }
    override fun onActivityPaused(activity: Activity) {
        super.onActivityPaused(activity)
        Logger.d("")
    }
    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)
        Logger.d("")
    }
    override fun notifyForeground() {
        super.notifyForeground()
        Logger.d("")
    }
    override fun notifyBackground() {
        super.notifyBackground()
        Logger.d("")
    }
}