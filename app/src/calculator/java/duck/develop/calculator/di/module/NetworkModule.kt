package duck.develop.calculator.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import duck.develop.calculator.R
import duck.develop.calculator.data.source.remote.implement.ConfigServiceImpl
import duck.develop.calculator.data.source.remote.service.ConfigService
import duck.develop.calculator.data.source.remote.service.KeyboardService
import duck.develop.calculator.di.Network.DEBUGGING_INTERCEPTOR
import duck.develop.calculator.di.Network.REQUEST_HEADER_INTERCEPTOR
import duck.develop.calculator.di.named
import duck.develop.calculator.util.LiveDataCallAdapterFactory
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hwang on 2019-07-10.
 *
 * Description :
 */
val network = module {
    //Service
    factory { get<Retrofit>().create(KeyboardService::class.java) }
    factory<ConfigService> { ConfigServiceImpl(get()) }

    //Module
    single {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.base_url))
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get())
            .build()
    }
    factory<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder()
            .create())
    }
    factory<CallAdapter.Factory> {
//        RxJava2CallAdapterFactory.createWithScheduler(get<SchedulerProvider>().io())
//        RxJava2CallAdapterFactory.create()
        LiveDataCallAdapterFactory()
    }
    factory {
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addNetworkInterceptor(get(named(DEBUGGING_INTERCEPTOR)))
            .addInterceptor(get(named(REQUEST_HEADER_INTERCEPTOR)))
            .authenticator(get())
            .build()
    }
    /* #Application Interceptor, Network Interceptor 차이점
     *
     *    Application
     *  ────────
     *  │Request    ↑ Response  ┐
     *  │           │           │→ Application
     *  ↓           │           ┘   Interceptors
     * ┌───────────┐
     * │     OkHttp Core      │
     * └───────────┘
     *  ↓   ↑   │Request   ↑Response ┐
     *   Cache    │          │         │→  Network
     *            ↓          │         ┘    Interceptors
     *            ───────
     *                Network
     */
    factory<Interceptor>(named(DEBUGGING_INTERCEPTOR)) {
        StethoInterceptor()
    }
    factory(named(REQUEST_HEADER_INTERCEPTOR)) {
        Interceptor { chain -> chain.proceed(chain.request()) }
    }
    factory {
        Authenticator { _, response -> response.request() }
    }
}