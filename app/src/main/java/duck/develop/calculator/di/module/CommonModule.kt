package duck.develop.calculator.di.module

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import duck.develop.calculator.R
import duck.develop.calculator.data.source.ConfigDataSource
import duck.develop.calculator.di.Repository
import duck.develop.calculator.di.Resources.APP_NAME
import duck.develop.calculator.di.Resources.BASE_URL
import duck.develop.calculator.di.named
import duck.develop.calculator.runner.RxSchedulerProvider
import duck.develop.calculator.runner.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 공통 모듈
 */
val common = module {
//    single(named(APP_NAME), createdAtStart = true/*모듈이 생성되는 시점에서 인스턴스 생성하는 옵션*/) { Key(1, 1, "C", 1) }
    factory { FirebaseRemoteConfig.getInstance() }

    factory(named(APP_NAME)) { androidContext().getString(R.string.app_name) }
    factory(named(BASE_URL)) { get<ConfigDataSource>(named(Repository.CONFIG_REPOSITORY)).getBaseUrl() }

    factory<SchedulerProvider> { RxSchedulerProvider() }
}