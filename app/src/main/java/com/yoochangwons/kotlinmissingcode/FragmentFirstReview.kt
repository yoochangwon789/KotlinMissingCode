package com.yoochangwons.kotlinmissingcode

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewBinding
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewItemBinding

class FragmentFirstReview : Fragment() {

    private var _binding: FragmentFirstReviewBinding? = null
    private val binding get() = _binding!!

    private val model: FragmentFirstViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstReviewBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.frFirstRecyclerView.apply {
            adapter = FragmentFirstRecyclerViewAdapter(
                emptyList(),
                fragmentToggleIcon = {
                    model.fragmentToggleTodoList(it)
                },
                fragmentDeleteIcon = {
                    model.fragmentDeleteTodoList(it)
                }
            )
            layoutManager = LinearLayoutManager(activity)
        }

        binding.frFirstButton.setOnClickListener {
            val todoList = binding.frFirstEditText.text.toString()
            model.fragmentAddTodoList(FragmentTodoList(todoList))
        }

        activity?.let {
            model.fragmentFirstLiveData.observe(it, Observer {
                ((binding.frFirstRecyclerView.adapter) as FragmentFirstRecyclerViewAdapter).setData(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class FragmentTodoList(val text: String, var isDone: Boolean = false)

class FragmentFirstRecyclerViewAdapter(
    private var dataSet: List<FragmentTodoList>,
    private val fragmentToggleIcon: (todoList : FragmentTodoList) -> Unit,
    private val fragmentDeleteIcon: (todoList : FragmentTodoList) -> Unit
) : RecyclerView.Adapter<FragmentFirstRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemViewBinding: FragmentFirstReviewItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_first_review_item, viewGroup, false)

        return ViewHolder(FragmentFirstReviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemViewBinding.frFirstTextViewItem.text = dataSet[position].text

        if (dataSet[position].isDone) {
            viewHolder.itemViewBinding.frFirstTextViewItem.apply {
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            viewHolder.itemViewBinding.frFirstTextViewItem.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        viewHolder.itemViewBinding.root.setOnClickListener {
            fragmentToggleIcon(dataSet[position])
        }

        viewHolder.itemViewBinding.frFirstImageViewItem.setOnClickListener {
            fragmentDeleteIcon(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(newData: List<FragmentTodoList>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}

class FragmentFirstViewModel : ViewModel() {
    val fragmentFirstLiveData = MutableLiveData<List<FragmentTodoList>>()

    private val fragmentFirstTodoArrayList = ArrayList<FragmentTodoList>()

    fun fragmentAddTodoList(todoList: FragmentTodoList) {
        fragmentFirstTodoArrayList.add(todoList)
        fragmentFirstLiveData.value = fragmentFirstTodoArrayList
    }

    fun fragmentToggleTodoList(todoList: FragmentTodoList) {
        todoList.isDone = !todoList.isDone
        fragmentFirstLiveData.value = fragmentFirstTodoArrayList
    }

    fun fragmentDeleteTodoList(todoList: FragmentTodoList) {
        fragmentFirstTodoArrayList.remove(todoList)
        fragmentFirstLiveData.value = fragmentFirstTodoArrayList
    }
}
