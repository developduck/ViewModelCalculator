package duck.develop.calculator.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import duck.develop.calculator.R
import duck.develop.calculator.databinding.FragmentMoviesBinding
import duck.develop.calculator.task.DefaultFragment
import duck.develop.calculator.task.view.adapter.MoviesAdapter
import duck.develop.calculator.task.viewmodel.MoviesViewModel
import duck.develop.core.manager.Logger
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MoviesFragment: DefaultFragment() {
    private lateinit var viewDataBinding: FragmentMoviesBinding

    private val viewModel by inject<MoviesViewModel>()
    private val adapter = MoviesAdapter().apply {
        onClick = { v, _, item ->
            Toast.makeText(activity, "${v.javaClass.simpleName}:${item.title.get()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false).also { v ->
            viewDataBinding = FragmentMoviesBinding.bind(v).apply {
                viewModel = this@MoviesFragment.viewModel
            }.apply {
                lifecycleOwner = viewLifecycleOwner
                list = ObservableArrayList()
                lstMovies.adapter = this@MoviesFragment.adapter
                lstMovies.layoutManager = LinearLayoutManager(activity)
            }
            viewModel.start()
            viewModel.movies.observe(this, Observer {
                viewDataBinding.list?.addAll(it)
            })
        }
    }
}