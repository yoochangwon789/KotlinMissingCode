package com.yoochangwons.kotlinmissingcode

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.ActivityRecyclerViewBinding
import com.yoochangwons.kotlinmissingcode.databinding.RecyclerViewItemBinding
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private val model: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            adapter = RecyclerViewAdapter(
                emptyList(),
                deleteTodoListIcon = {
                    model.deleteTodoList(it)
                },
                toggleTodoListIcon = {
                    model.toggleTodoList(it)
                }
            )
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        }

        binding.recyclerViewButton.setOnClickListener {
            val todolist = TodoList(binding.recyclerViewEditText.text.toString())
            model.addTodoList(todolist)
        }

        model.todoLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as RecyclerViewAdapter).setData(it)
        })
    }
}

data class TodoList(var todoText: String, var isDone: Boolean = false)

class RecyclerViewAdapter(
    private var data: List<TodoList>,
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

    fun setData(newData: List<TodoList>) {
        data = newData
        notifyDataSetChanged()
    }
}

class MyViewModel : ViewModel() {
    val todoLiveData = MutableLiveData<List<TodoList>>()

    private val todoArrayList = ArrayList<TodoList>()

    fun addTodoList(todoList: TodoList) {
        todoArrayList.add(todoList)
        todoLiveData.value = todoArrayList
    }

    fun deleteTodoList(todoList: TodoList) {
        todoArrayList.remove(todoList)
        todoLiveData.value = todoArrayList
    }

    fun toggleTodoList(todolist: TodoList) {
        todolist.isDone = !todolist.isDone
        todoLiveData.value = todoArrayList
    }
}