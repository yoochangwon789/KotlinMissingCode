package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.ActivityRecyclerViewBinding
import com.yoochangwons.kotlinmissingcode.databinding.RecyclerViewItemBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

data class TodoList(val todoText: String, val isDone: Boolean = false)

class RecyclerViewAdapter(
    private val data: List<TodoList>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val recyclerViewItemBinding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(recyclerViewItemBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(RecyclerViewItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewItemBinding.recyclerItemTextView.text = data[position].todoText
    }

    override fun getItemCount(): Int {
        return data.size
    }
}