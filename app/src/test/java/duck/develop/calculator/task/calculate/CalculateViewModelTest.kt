package duck.develop.calculator.task.calculate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.res.TypedArrayUtils.getText
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import duck.develop.calculator.LiveDataTestUtil.getValue
import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.task.viewmodel.CalculateViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class CalculateViewModelTest {
    private lateinit var dataSource: KeyboardDataSource
    private lateinit var viewModel: CalculateViewModel

//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)

        //Data : 데이터
        val dummy: SelectKeyboardJoinKeyAll = SelectKeyboardJoinKeyAll(1, 5).apply {
            keys = listOf(Key(1, 1, "CE", "ce", 0))
        }

        //Mock : 가상 객체
        dataSource = mock {
            //Given : 상황(Case)
            on { runBlocking { getKeyboardJoinKeyAll(1) } } doReturn Result.Success(dummy)
        }
        viewModel = CalculateViewModel(dataSource)
    }
    @After
    fun after() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun initResultTest() {
        //When : 로직(Logic)
        viewModel.start("1")

        //Then : 검증(Verify)
        Truth.assertThat(getValue(viewModel.result)).isEqualTo("1")
    }

    @Test
    fun loadKeyboardTest() = runBlockingTest {
        //When : 로직(Logic)
        viewModel.loadKeyboard(1080, 1920)

        //Then : 검증(Verify)
        Truth.assertThat(getValue(viewModel.keyboard).id).isEqualTo(1)
        Truth.assertThat(getValue(viewModel.keys)[0].display).isEqualTo("CE")
    }
}