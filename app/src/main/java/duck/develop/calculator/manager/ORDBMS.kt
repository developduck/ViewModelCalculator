package duck.develop.calculator.manager

import androidx.room.Database
import androidx.room.RoomDatabase
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.data.source.local.dao.KeyDataAccessObj
import duck.develop.calculator.data.source.local.dao.KeyboardDataAccessObj

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : Room 데이터 베이스 추상 객체
 */
@Database(entities = [Keyboard::class, Key::class], version = 2, exportSchema = false)
abstract class ORDBMS: RoomDatabase() {
    abstract fun keyDao(): KeyDataAccessObj
    abstract fun keyboardDao(): KeyboardDataAccessObj
}