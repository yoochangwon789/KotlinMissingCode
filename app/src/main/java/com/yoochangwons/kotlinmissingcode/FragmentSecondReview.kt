package com.yoochangwons.kotlinmissingcode

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
import com.bumptech.glide.Glide
import com.yoochangwons.kotlinmissingcode.databinding.FragmentSecondReviewBinding
import com.yoochangwons.kotlinmissingcode.databinding.FragmentSecondReviewItemBinding

class FragmentSecondReview : Fragment() {

    private var _binding: FragmentSecondReviewBinding? = null
    private val binding get() = _binding!!

    private val model: FragmentSecondViewModel by activityViewModels()

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

        binding.frSecondRecyclerView.apply {
            adapter = FragmentSecondRecyclerViewAdapter(emptyList(), this)
            layoutManager = LinearLayoutManager(activity)
        }

        binding.frSecondButton.setOnClickListener {
            val text = binding.frSecondEditText.text.toString()
            model.addTalent(FragmentTalentList(text))
        }

        activity?.let {
            model.talentLiveData.observe(it, Observer {
                (binding.frSecondRecyclerView.adapter as FragmentSecondRecyclerViewAdapter)
                    .setData(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class FragmentTalentList(val text: String)

class FragmentSecondRecyclerViewAdapter(
    private var dataSet: List<FragmentTalentList>,
    private val fragmentActivity: RecyclerView
) : RecyclerView.Adapter<FragmentSecondRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: FragmentSecondReviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_second_review_item, viewGroup, false)

        return ViewHolder(FragmentSecondReviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemBinding.frSecondTextView.text = dataSet[position].text

        Glide.with(fragmentActivity)
            .load("https://file.mk.co.kr/meet/neds/2021/04/image_readtop_2021_330747_16177500644599916.jpg")
            .into(viewHolder.itemBinding.frSecondImageView)
    }

    override fun getItemCount() = dataSet.size

    fun setData(newData: List<FragmentTalentList>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}

class FragmentSecondViewModel : ViewModel() {
    val talentLiveData = MutableLiveData<List<FragmentTalentList>>()

    private val talentArrayList = ArrayList<FragmentTalentList>()

    fun addTalent(talent: FragmentTalentList) {
        talentArrayList.add(talent)
        talentLiveData.value = talentArrayList
    }
}