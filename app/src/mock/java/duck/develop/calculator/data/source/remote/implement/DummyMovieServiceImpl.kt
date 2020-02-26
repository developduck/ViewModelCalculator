package duck.develop.calculator.data.source.remote.implement

import com.squareup.moshi.*
import duck.develop.calculator.data.adapter.ObservableFieldStringAdapter
import duck.develop.calculator.data.model.Root
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.data.source.remote.service.MovieService
import java.lang.reflect.ParameterizedType

/**
 * Created by Hwang on 2019-10-23.
 *
 * Description : 영화 서비스의 더미 데이터
 */
class DummyMovieServiceImpl: MovieService {
    override suspend fun getMovies(page: Int, rowPerPage: Int): Root<List<Movie>> {
        val dummyJson = "{\"code\":200,\"message\":\"Success\",\"data\":[{\"id\":1,\"title\":\"dummy_title01\",\"order\":0},{\"id\":2,\"title\":\"title02\",\"order\":1},{\"id\":3,\"title\":\"title03\",\"order\":2},{\"id\":4,\"title\":\"title04\",\"order\":3},{\"id\":5,\"title\":\"title05\",\"order\":4},{\"id\":6,\"title\":\"title06\",\"order\":5},{\"id\":7,\"title\":\"title07\",\"order\":6},{\"id\":8,\"title\":\"title08\",\"order\":7},{\"id\":9,\"title\":\"title09\",\"order\":8},{\"id\":10,\"title\":\"title10\",\"order\":9}]}"
        return getMoshiParsing(dummyJson, Types.newParameterizedType(List::class.java, Types.newParameterizedType(Movie::class.java)))
    }

    private fun <E> getMoshiParsing(dummyJson: String, type: ParameterizedType): Root<E> {
        return Moshi.Builder()
            .add(ObservableFieldStringAdapter())
            .build()
            .adapter<Root<E>>(Types.newParameterizedType(Root::class.java, type))
            .fromJson(dummyJson)!!
    }
}