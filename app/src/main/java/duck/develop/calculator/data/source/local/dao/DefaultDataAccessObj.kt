package duck.develop.calculator.data.source.local.dao

import androidx.room.*

/**
 * Created by Hwang on 2019-06-28.
 *
 * Description : 기본 DML(data manipulation language)
 */
interface DefaultDataAccessObj<OBJ> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: OBJ): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: OBJ)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<OBJ>)

    @Update
    fun update(obj: OBJ)
    @Update
    fun update(vararg obj: OBJ)
    @Update
    fun update(list: List<OBJ>)

    @Delete
    fun delete(obj: OBJ)
    @Delete
    fun delete(vararg obj: OBJ)
    @Delete
    fun delete(list: List<OBJ>)
}