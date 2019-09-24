package duck.develop.calculator.di.module

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import duck.develop.calculator.di.Database.MIGRATION_1_2
import duck.develop.calculator.di.named
import duck.develop.calculator.manager.ORDBMS
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Hwang on 2019-07-01.
 *
 * Description : 데이터 베이스 모듈
 */
val database = module {
    single(createdAtStart = true) {
        Room.databaseBuilder(androidContext() , ORDBMS::class.java, "calculator.db")
//            .addMigrations(get<Migration>(named(MIGRATION_1_2)))
            .fallbackToDestructiveMigration()
//            .addCallback(object: RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    Executors.newSingleThreadExecutor().execute {
//                        val keyboard = Keyboard(1, 4)
//                        val keyViews = listOf(
//                            "CE", "C", "<", "/",
//                            "7", "8", "9", "*",
//                            "4", "5", "6", "-",
//                            "1", "2", "3", "+",
//                            "±", "0", ".", "="
//                        )
//                        val keys = ArrayList<Key>()
//                        for (i in 0 until keyViews.size) {
//                            keys.add(Key(i + 1, 1, keyViews[i], i))
//                        }
//                        get<ORDBMS>().keyboardDao().insert(keyboard)
//                        get<ORDBMS>().keyDao().insert(keys)
//                    }
//                }
//            })
            .build()
    }
    factory(named(MIGRATION_1_2)) {
        object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("")
            }
        }
    }
    factory { get<ORDBMS>().keyDao() }
    factory { get<ORDBMS>().keyboardDao() }
}