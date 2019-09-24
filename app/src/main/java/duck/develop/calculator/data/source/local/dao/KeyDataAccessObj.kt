package duck.develop.calculator.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import duck.develop.calculator.data.model.entity.Key
import io.reactivex.Single

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 키 관련 DAO
 */
@Dao
interface KeyDataAccessObj: DefaultDataAccessObj<Key> {
    @Transaction
    fun deleteAllAndInsert(keys: List<Key>) {
        deleteAll()
        insert(keys)
    }

    @Query("SELECT * FROM `key` WHERE id = :id")
    fun getKey(id: Int): Key
    @Query("SELECT * FROM `key` WHERE keyboard_id = :keyboard_id ORDER BY `order` ASC")
    fun getKeys(keyboard_id: Int): List<Key>
    @Query("DELETE FROM `key`")
    fun deleteAll()
}