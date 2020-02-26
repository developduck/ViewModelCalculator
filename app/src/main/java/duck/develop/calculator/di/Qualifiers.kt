@file:JvmName("QualifiersKt")

package duck.develop.calculator.di

import org.koin.core.qualifier.Qualifier

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 의존성 주입 모듈의 각 인스턴스들의 키값
 */
fun named(value: Resources) = EnumQualifier(value)
fun named(value: Repository) = EnumQualifier(value)
fun named(value: Database) = EnumQualifier(value)
fun named(value: Network) = EnumQualifier(value)

enum class Resources {
    APP_NAME,
    BASE_URL
}
enum class Repository {
    KEYBOARD_REPOSITORY,
    KEYBOARD_LOCAL_DATA_SOURCE,
    KEYBOARD_REMOTE_DATA_SOURCE,
    CONFIG_REPOSITORY,
    CONFIG_LOCAL_DATA_SOURCE,
    CONFIG_REMOTE_DATA_SOURCE,
    MOVIE_REPOSITORY,
    MOVIE_REMOTE_DATA_SOURCE
}
enum class Database {
    MIGRATION_1_2
}
enum class Network {
    DEBUGGING_INTERCEPTOR,
    REQUEST_HEADER_INTERCEPTOR
}

data class EnumQualifier<ENUM>(val value: ENUM) : Qualifier {
    override fun toString(): String {
        return value.toString()
    }
}