package duck.develop.calculator.data.source.local.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.manager.ORDBMS
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declare
import java.util.*

/**
 * Created by Hwang on 2019-07-03.
 *
 * Description : Koin을 이용한 DAO 테스트
 */
@RunWith(AndroidJUnit4::class)
class KeyboardDataAccessObjTest: KoinTest {
    private val db: ORDBMS by inject()
    private val keyDao: KeyDataAccessObj by inject()
    private val keyboardDao: KeyboardDataAccessObj by inject()

    @Before fun setup() {
        declare {
            single {
                Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, ORDBMS::class.java)
                    .build()
            }
            factory { get<ORDBMS>().keyboardDao() }
        }
        keyboardDao.insert(Keyboard(1))
    }
    @Throws(Exception::class)
    @After fun close() {
        db.close()
    }

    @Throws(Exception::class)
    @Test fun basicKeyDataAccess() {
        val key = Key(1, 1, "CE", "ce", 0, "flat_symbol")

        keyDao.insert(key)
        keyDao.getKey(key.id)
            .subscribe({
                TestCase.assertEquals(it.id, key.id)
                TestCase.assertEquals(it.display, key.display)

                keyDao.delete(key)
            }, {
                throw it
            })
    }
    @Throws(Exception::class)
    @Test fun getKeyListDataAccess() {
        val key = Key(1, 1, "CE", "ce", 0)
        val keys = ArrayList(listOf(key.copy(id = 2, order = 6), key.copy(id = 3, order = 5), key))

        keyDao.insert(keys)
        keyDao.getKeys(1)
            .subscribe({
                TestCase.assertEquals(it.size, keys.size)

                TestCase.assertEquals(it[0].id, 1)
                TestCase.assertEquals(it[0].order, 0)

                TestCase.assertEquals(it[keys.size - 1].id, 2)
                TestCase.assertEquals(it[keys.size - 1].order, 6)

                keyDao.delete(it)
            }, {
                throw it
            })
    }
    @Throws(java.lang.Exception::class)
    @Test fun getKeyboardJoinKeyListDataAccess() {
        val keys = ArrayList<Key>()
        for (i in 1 until 10) {
            keys.add(Key(i, 1, "Key$i", "number", i))
        }
        keyDao.insert(keys)
        keyboardDao.getKeyboardJoinKeyAll(1)
//            .subscribe({
//                TestCase.assertEquals(it.keys.size, 9)
//            }, {
//                throw it
//            })
    }
    @Throws(java.lang.Exception::class)
    @Test fun checkForeignKeyConstraintOnKeyEntities() {
        val keys = ArrayList<Key>()
        for (i in 1 until 10) {
            keys.add(Key(i, 1, "Key$i", "number", i))
        }
        keyDao.insert(keys)
        keyboardDao.getKeyboards()
//            .flatMap { item ->
//                keyDao.getKeys(1)
//                    .map {
//                        TestCase.assertEquals(it.size, 9)
//                        item
//                    }
//            }
//            .map {
//                TestCase.assertEquals(it.size, 1)
//                TestCase.assertEquals(it[0].id, 1)
//
//                keyboardDao.delete(it)
//            }
//            .flatMap {
//                keyDao.getKeys(1)
//                    .map {
//                        TestCase.assertEquals(it.size, 0)
//                    }
//            }
//            .subscribe()
    }
}