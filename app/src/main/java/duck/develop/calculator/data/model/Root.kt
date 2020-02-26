package duck.develop.calculator.data.model

/**
 * Created by Hwang on 2019-07-25.
 *
 * Description : 데이터를 감싸는 최상위 클래스 (주로 네트워크에서 사용됨)
 *               * 뷰 모델을 사용할 경우 데이터가 라이프 사이클에 독립적이 되면서 Parcelable을 사용할 필요성이 있는가에 대한 고민이 필요함
 */
data class Root<DATA>(
    val code: Int? = null,
    val message: String? = null,
    val data: DATA? = null
)