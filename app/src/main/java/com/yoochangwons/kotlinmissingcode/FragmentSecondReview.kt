package com.yoochangwons.kotlinmissingcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoochangwons.kotlinmissingcode.databinding.FragmentSecondReviewBinding
import com.yoochangwons.kotlinmissingcode.databinding.FragmentSecondReviewItemBinding

class FragmentSecondReview : Fragment() {

    private var _binding: FragmentSecondReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondReviewBinding.inflate(inflater, container, false)
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

data class FragmentTalentList(val text: String)

class FragmentSecondRecyclerViewAdapter(private val dataSet: List<FragmentTalentList>) :
    RecyclerView.Adapter<FragmentSecondRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: FragmentSecondReviewItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_second_review_item, viewGroup, false)

        return ViewHolder(FragmentSecondReviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemBinding.frSecondTextView.text = dataSet[position].text
    }

    override fun getItemCount() = dataSet.size

}

class FragmentSecondViewModel : ViewModel() {
    val talentLiveData = MutableLiveData<List<FragmentTalentList>>()

    private val talentList = ArrayList<FragmentTalentList>()


}