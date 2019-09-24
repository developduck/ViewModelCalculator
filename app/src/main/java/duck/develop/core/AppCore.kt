package duck.develop.core

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import duck.develop.core.manager.ExceptionHandler

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description :
 */
@SuppressLint("Registered")
open class AppCore: Application(), LifecycleObserver {
    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            appCore.onActivityCreated(activity, savedInstanceState)
        }
        override fun onActivityStarted(activity: Activity) {
            appCore.onActivityStarted(activity)
        }
        override fun onActivityResumed(activity: Activity) {
            appCore.onActivityResumed(activity)
        }
        override fun onActivityPaused(activity: Activity) {
            appCore.onActivityPaused(activity)
        }
        override fun onActivityStopped(activity: Activity) {
            appCore.onActivityStopped(activity)
        }
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            appCore.onActivitySaveInstanceState(activity, outState)
        }
        override fun onActivityDestroyed(activity: Activity) {
            appCore.onActivityDestroyed(activity)
        }
    }

    companion object {
        private lateinit var appCore: AppCore
    }

    override fun onCreate() {
        super.onCreate()
        appCore = this
        // 앱 생명주기
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        // 전역 예외 핸들러
        ExceptionHandler()
    }

    protected open fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    protected open fun onActivityStarted(activity: Activity) {}
    protected open fun onActivityResumed(activity: Activity) {}
    protected open fun onActivityPaused(activity: Activity) {}
    protected open fun onActivityStopped(activity: Activity) {}
    protected open fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    protected open fun onActivityDestroyed(activity: Activity) {}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun notifyForeground() {
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun notifyBackground() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}