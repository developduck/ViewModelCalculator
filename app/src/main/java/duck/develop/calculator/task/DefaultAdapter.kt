package duck.develop.calculator.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DefaultAdapter<ITEM, BINDER: ViewDataBinding>:
    RecyclerView.Adapter<DefaultAdapter<ITEM, BINDER>.ViewHolder>() {
    abstract val layout: Int

    var items: List<ITEM>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onClick: ((v: View, position: Int, item: ITEM) -> Unit)? = null
    var selectedItem: ITEM? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return onCreateViewHolder(inflate(layout, parent))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }
    }
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    private fun inflate(@LayoutRes resId: Int, parent: ViewGroup): BINDER =
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), resId, parent, false)

    abstract fun onCreateViewHolder(binder: BINDER): ViewHolder

    open inner class ViewHolder(
        private val binding: BINDER,
        private vararg val bindingIds: Int?
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    items?.get(adapterPosition)?.let { item -> click(v, adapterPosition, item) }
                }
            }
        }
        open fun bind(item: ITEM) {
            for (bindingId in bindingIds) {
                bindingId?.let { binding.setVariable(it, item) }
            }
        }
        open fun click(v: View, position: Int, item: ITEM) {
            onClick?.invoke(v, position, item)
            selectedItem = item
        }
    }
}