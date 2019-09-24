package duck.develop.calculator.data.source.remote.implement

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import duck.develop.calculator.data.model.Root
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.remote.service.KeyboardService

/**
 * Created by Hwang on 2019-07-25.
 *
 * Description : 키보드 서비스의 더미 데이터
 */
class DummyKeyboardServiceImpl: KeyboardService {
    override suspend fun getKeyboardJoinKeyAll(id: Int): Root<SelectKeyboardJoinKeyAll> {
        val dummyJson = "{\"code\":200,\"message\":\"Success\",\"data\":{\"version\":1566457264000,\"id\":1,\"column_count\":4,\"keys\":[{\"id\":1,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"%\",\"command\":\"none\",\"order\":0},{\"id\":2,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"√\",\"command\":\"none\",\"order\":1},{\"id\":3,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"(\",\"command\":\"none\",\"order\":2},{\"id\":4,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\")\",\"command\":\"none\",\"order\":3},{\"id\":5,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"CE\",\"command\":\"ce\",\"order\":4},{\"id\":6,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"C\",\"command\":\"c\",\"order\":5},{\"id\":7,\"keyboard_id\":1,\"style\":\"flat_icon\",\"display\":\"<\",\"command\":\"delete\",\"order\":6},{\"id\":8,\"keyboard_id\":1,\"style\":\"flat_operator\",\"display\":\"/\",\"command\":\"divide\",\"order\":7},{\"id\":9,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"7\",\"command\":\"number\",\"order\":8},{\"id\":10,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"8\",\"command\":\"number\",\"order\":9},{\"id\":11,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"9\",\"command\":\"number\",\"order\":10},{\"id\":12,\"keyboard_id\":1,\"style\":\"flat_operator\",\"display\":\"*\",\"command\":\"multiply\",\"order\":11},{\"id\":13,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"4\",\"command\":\"number\",\"order\":12},{\"id\":14,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"5\",\"command\":\"number\",\"order\":13},{\"id\":15,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"6\",\"command\":\"number\",\"order\":14},{\"id\":16,\"keyboard_id\":1,\"style\":\"flat_operator\",\"display\":\"-\",\"command\":\"minus\",\"order\":15},{\"id\":17,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"1\",\"command\":\"number\",\"order\":16},{\"id\":18,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"2\",\"command\":\"number\",\"order\":17},{\"id\":19,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"3\",\"command\":\"number\",\"order\":18},{\"id\":20,\"keyboard_id\":1,\"style\":\"flat_operator\",\"display\":\"+\",\"command\":\"plus\",\"order\":19},{\"id\":21,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\"±\",\"command\":\"none\",\"order\":20},{\"id\":22,\"keyboard_id\":1,\"style\":\"flat_number\",\"display\":\"0\",\"command\":\"number\",\"order\":21},{\"id\":23,\"keyboard_id\":1,\"style\":\"flat_symbol\",\"display\":\".\",\"command\":\"none\",\"order\":22},{\"id\":24,\"keyboard_id\":1,\"style\":\"flat_operator\",\"display\":\"=\",\"command\":\"equal\",\"order\":23}]}}"
        return Gson().fromJson(dummyJson, object: TypeToken<Root<SelectKeyboardJoinKeyAll>>() {}.type) as Root<SelectKeyboardJoinKeyAll>
    }
}