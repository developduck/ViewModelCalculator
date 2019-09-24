package duck.develop.calculator.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키보드 관련 DAO
 */
@Dao
interface KeyboardDataAccessObj: RelationDataAccessObj<Keyboard, Key> {
    @Transaction
    suspend fun deleteAllAndInsert(keyboards: List<SelectKeyboardJoinKeyAll>) {
        deleteAll()
        for (keyboard in keyboards) {
            insert(Keyboard(keyboard), keyboard.keys)
        }
    }

    @Query("SELECT * FROM keyboard WHERE id = :id")
    suspend fun getKeyboard(id: Int): Keyboard
    @Query("SELECT * FROM keyboard")
    suspend fun getKeyboards(): List<Keyboard>
    @Transaction
    @Query("SELECT version FROM keyboard WHERE id = :id")
    suspend fun getKeyboardVersion(id: Int): Long
    @Transaction
    @Query("SELECT * FROM keyboard WHERE id = :id")
    suspend fun getKeyboardJoinKeyAll(id: Int): SelectKeyboardJoinKeyAll
    @Query("DELETE FROM `keyboard`")
    suspend fun deleteAll()
}