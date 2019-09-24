package duck.develop.calculator.task.calculate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth
import duck.develop.calculator.awaitNextValue
import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Key
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import duck.develop.calculator.data.source.KeyboardDataSource
import duck.develop.calculator.task.viewmodel.CalculateViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

class CalculateViewModelTest {
    private lateinit var dataSource: KeyboardDataSource
    private lateinit var viewModel: CalculateViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        //Mock : 가상 객체
        dataSource = mock(KeyboardDataSource::class.java)

        //Data : 데이터
        val dummy: SelectKeyboardJoinKeyAll = SelectKeyboardJoinKeyAll(1, 5).apply {
            keys = listOf(Key(1, 1, "CE", "ce", 0))
        }

        //Given : 상황(Case)
        doReturn(object: LiveData<Result<SelectKeyboardJoinKeyAll>>() {
            override fun onActive() {
                super.onActive()
                postValue(Result.Success(dummy))
            }
        }).`when`(dataSource)
            .getKeyboardJoinKeyAll(ArgumentMatchers.eq(1))

        viewModel = CalculateViewModel(dataSource)
    }
    @After
    fun after() {
    }

    @Test
    fun initResultTest() {
        //When : 로직(Logic)
        viewModel.start("1")

        //Then : 검증(Verify)
        Truth.assertThat(viewModel.result.awaitNextValue()).isEqualTo("1")
    }

    @Test
    fun loadKeyboardTest() {
        //When : 로직(Logic)
        viewModel.loadKeyboard(1080, 1920)

        //Then : 검증(Verify)
        Truth.assertThat(viewModel.keyboard.awaitNextValue().id).isEqualTo(1)
        Truth.assertThat(viewModel.keys.awaitNextValue()[0].display).isEqualTo("CE")
    }
}