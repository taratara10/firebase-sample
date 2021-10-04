package com.kabos.firebasesample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kabos.firebasesample.databinding.ActivityMainBinding.inflate
import com.kabos.firebasesample.databinding.AdapterMemoBinding
import com.kabos.firebasesample.model.MemoItem

class MemoAdapter(): androidx.recyclerview.widget.ListAdapter<MemoItem, MemoAdapter.MemoViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterMemoBinding.inflate(layoutInflater, parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MemoViewHolder(private val binding: AdapterMemoBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MemoItem){
            binding.apply {
                tvAdapterTitle.text = item.title
                tvAdapterCreatedAt.text = item.title
            }
        }
    }
}

private object DiffCallback: DiffUtil.ItemCallback<MemoItem>(){
    override fun areItemsTheSame(oldItem: MemoItem, newItem: MemoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MemoItem, newItem: MemoItem): Boolean {
        return oldItem.title == newItem.title
    }

}
