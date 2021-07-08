package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewBinding
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewItemBinding

class FragmentFirstReview : Fragment() {

    private var _binding: FragmentFirstReviewBinding? = null
    private val binding get() = _binding!!

    private val fragmentFirstTodoArrayList = ArrayList<FragmentTodoList>()

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
            adapter = FragmentFirstRecyclerViewAdapter(fragmentFirstTodoArrayList)
            layoutManager = LinearLayoutManager(activity)
        }

        binding.frFirstButton.setOnClickListener {
            fragmentAddTodoList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fragmentAddTodoList() {
        val text = binding.frFirstEditText.text.toString()
        fragmentFirstTodoArrayList.add(FragmentTodoList(text))
        binding.frFirstRecyclerView.adapter?.notifyDataSetChanged()
    }
}

data class FragmentTodoList(val text: String, val isDone: Boolean = false)

class FragmentFirstRecyclerViewAdapter(
    private val dataSet: List<FragmentTodoList>
) : RecyclerView.Adapter<FragmentFirstRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemViewBinding: FragmentFirstReviewItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_first_review_item, viewGroup, false)

        return ViewHolder(FragmentFirstReviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemViewBinding.frFirstTextViewItem.text = dataSet[position].text
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
