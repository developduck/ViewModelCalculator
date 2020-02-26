package duck.develop.calculator.task.view.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import duck.develop.calculator.BR
import duck.develop.calculator.R
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.databinding.ItemMovieBinding
import duck.develop.calculator.task.DefaultAdapter

@BindingAdapter("app:item")
fun bindItem(list: RecyclerView, items: ObservableArrayList<Movie>) = list.adapter?.let {
    (it as MoviesAdapter).run { this.items = items }
}
class MoviesAdapter: DefaultAdapter<Movie, ItemMovieBinding>() {
    override val layout: Int
        get() = R.layout.item_movie

    override fun onCreateViewHolder(binder: ItemMovieBinding): DefaultAdapter<Movie, ItemMovieBinding>.ViewHolder = ViewHolder(binder)

    inner class ViewHolder(binding: ItemMovieBinding): DefaultAdapter<Movie, ItemMovieBinding>.ViewHolder(binding, BR.item) {
        init { binding.holder = this }
    }
}