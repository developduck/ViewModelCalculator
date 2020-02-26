package duck.develop.calculator.di.module

import duck.develop.calculator.data.source.remote.implement.ConfigServiceImpl
import duck.develop.calculator.data.source.remote.implement.DummyKeyboardServiceImpl
import duck.develop.calculator.data.source.remote.implement.DummyMovieServiceImpl
import duck.develop.calculator.data.source.remote.service.ConfigService
import duck.develop.calculator.data.source.remote.service.KeyboardService
import duck.develop.calculator.data.source.remote.service.MovieService
import org.koin.dsl.module

/**
 * Created by Hwang on 2019-07-10.
 *
 * Description : Mock(Dummy) 네트워크 모듈
 */
val network = module {
    //Mock
    factory<KeyboardService> { DummyKeyboardServiceImpl() }
    factory<ConfigService> { ConfigServiceImpl(get()) }
    factory<MovieService> { DummyMovieServiceImpl() }
}