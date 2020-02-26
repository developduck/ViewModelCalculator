package duck.develop.calculator.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.data.source.repository.MovieRepository
import duck.develop.calculator.task.DefaultViewModel
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MovieRepository
): DefaultViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun start() = viewModelScope.launch {
        withErrorHandler(repository.getMovies(1, 10)) {
            _movies.value = it
        }
    }
}