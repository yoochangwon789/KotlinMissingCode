package com.yoochangwons.kotlinmissingcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewBinding
import com.yoochangwons.kotlinmissingcode.databinding.FragmentFirstReviewItemBinding

class FragmentFirstReview : Fragment() {

    private var _binding: FragmentFirstReviewBinding? = null
    private val binding get() = _binding!!

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


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class FragmentTodoList(val text: String, val isDone: Boolean)

class FragmentFirstRecyclerViewAdapter(
    private val dataSet: Array<String>
) : RecyclerView.Adapter<FragmentFirstRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemViewBinding: FragmentFirstReviewItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_first_review_item, viewGroup, false)

        return ViewHolder(FragmentFirstReviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = dataSet.size
}
