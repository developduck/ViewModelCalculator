package duck.develop.calculator.data.source.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Hwang on 2019-08-16.
 *
 * Description : 관계를 가지는 데이터를 처리하기 위한 DAO
 */
interface RelationDataAccessObj<OBJ, SUB>: DefaultDataAccessObj<OBJ> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: OBJ, list: List<SUB>)
}