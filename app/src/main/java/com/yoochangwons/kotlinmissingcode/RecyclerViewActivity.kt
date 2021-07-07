package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.ActivityRecyclerViewBinding
import com.yoochangwons.kotlinmissingcode.databinding.RecyclerViewItemBinding
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private val todoArrayList = ArrayList<TodoList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerViewButton.setOnClickListener {
            addTodoList()
        }

        val adapter = RecyclerViewAdapter(
            todoArrayList,
            deleteTodoListIcon = { deleteTodoList(it) }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addTodoList() {
        todoArrayList.add(TodoList(binding.recyclerViewEditText.text.toString()))
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun deleteTodoList(todoList: TodoList) {
        todoArrayList.remove(todoList)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class TodoList(var todoText: String, val isDone: Boolean = false)

class RecyclerViewAdapter(
    private val data: List<TodoList>,
    private val deleteTodoListIcon: (todolist: TodoList) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val recyclerViewItemBinding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(recyclerViewItemBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(RecyclerViewItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewItemBinding.recyclerItemTextView.text = data[position].todoText
        holder.recyclerViewItemBinding.recyclerItemImageView.setOnClickListener {
            deleteTodoListIcon(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}