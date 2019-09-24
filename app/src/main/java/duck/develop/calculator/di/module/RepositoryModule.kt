package duck.develop.calculator.di.module

import duck.develop.calculator.data.source.ConfigDataSource
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.data.source.local.ConfigLocalDataSource
import duck.develop.calculator.data.source.local.KeyboardLocalDataSource
import duck.develop.calculator.data.source.remote.ConfigRemoteDataSource
import duck.develop.calculator.data.source.remote.KeyboardRemoteDataSource
import duck.develop.calculator.data.source.repository.ConfigRepository
import duck.develop.calculator.data.source.repository.KeyboardRepository
import duck.develop.calculator.di.Repository.*
import duck.develop.calculator.di.named
import org.koin.dsl.module

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 저장소 모듈
 */
val repository = module {
    single<KeyboardDataSource>(named(KEYBOARD_REPOSITORY)) {
        KeyboardRepository(get(named(KEYBOARD_LOCAL_DATA_SOURCE)), get(named(KEYBOARD_REMOTE_DATA_SOURCE)))
    }
    single<KeyboardDataSource>(named(KEYBOARD_LOCAL_DATA_SOURCE)) { KeyboardLocalDataSource(get()) }
    single<KeyboardDataSource>(named(KEYBOARD_REMOTE_DATA_SOURCE)) { KeyboardRemoteDataSource(get()) }

    single<ConfigDataSource>(named(CONFIG_REPOSITORY)) {
        ConfigRepository(get(named(CONFIG_LOCAL_DATA_SOURCE)), get(named(CONFIG_REMOTE_DATA_SOURCE)))
    }
    single<ConfigDataSource>(named(CONFIG_LOCAL_DATA_SOURCE)) { ConfigLocalDataSource() }
    single<ConfigDataSource>(named(CONFIG_REMOTE_DATA_SOURCE)) { ConfigRemoteDataSource(get()) }
}