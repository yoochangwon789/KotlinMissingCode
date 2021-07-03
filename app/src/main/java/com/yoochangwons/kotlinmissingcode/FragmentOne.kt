package com.yoochangwons.kotlinmissingcode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yoochangwons.kotlinmissingcode.databinding.FragmentOneBinding

class FragmentOne : Fragment() {

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    interface OneDataPassListener {
        fun onDataPass(data: String?)
    }

    lateinit var dataPassListener: OneDataPassListener

    override fun onAttach(context: Context) {
        Log.d("fragment_life_cycle", "onAttach")
        super.onAttach(context)
        dataPassListener = context as OneDataPassListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onCreate")
        super.onCreate(savedInstanceState)
    }

    // Fragment 의 직접적인 뷰를 그리는 곳
    // Fragment 가 첫 인터페이스를 그릴 때 호출한다
    // inflater -> view 를 그려준다
    // container -> 부모뷰를 지정 (달라붙을 곳을 지정한다)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("fragment_life_cycle", "onCreateView")
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // Activity 의 onCreate 에서 했던 작업을 여기에서 한다
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onViewCreated")

        _binding?.pass?.setOnClickListener {
            dataPassListener.onDataPass("Good Bye")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onActivityCreated")

        val data = arguments?.getString("hello")
        Log.d("data", "$data")

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("fragment_life_cycle", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("fragment_life_cycle", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("fragment_life_cycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("fragment_life_cycle", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("fragment_life_cycle", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d("fragment_life_cycle", "onDetach")
        super.onDetach()
    }
}