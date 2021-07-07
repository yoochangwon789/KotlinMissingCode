package com.yoochangwons.kotlinmissingcode

import android.graphics.Paint
import android.graphics.Typeface
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

        binding.recyclerView.apply {
            adapter = RecyclerViewAdapter(
                todoArrayList,
                deleteTodoListIcon = {deleteTodoList(it)},
                toggleTodoListIcon = {toggleTodoList(it)}
            )
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        }

        binding.recyclerViewButton.setOnClickListener {
            addTodoList()
        }
    }

    private fun addTodoList() {
        todoArrayList.add(TodoList(binding.recyclerViewEditText.text.toString()))
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun deleteTodoList(todoList: TodoList) {
        todoArrayList.remove(todoList)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun toggleTodoList(todolist: TodoList) {
        todolist.isDone = !todolist.isDone
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class TodoList(var todoText: String, var isDone: Boolean = false)

class RecyclerViewAdapter(
    private val data: List<TodoList>,
    private val toggleTodoListIcon: (todoList: TodoList) -> Unit,
    private val deleteTodoListIcon: (todoList: TodoList) -> Unit
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

        if (data[position].isDone) {
            holder.recyclerViewItemBinding.recyclerItemTextView.apply {
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.recyclerViewItemBinding.recyclerItemTextView.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        holder.recyclerViewItemBinding.apply {
            root.setOnClickListener {
                toggleTodoListIcon(data[position])
            }
            recyclerItemImageView.setOnClickListener {
                deleteTodoListIcon(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}