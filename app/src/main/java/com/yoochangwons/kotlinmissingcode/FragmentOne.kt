package com.yoochangwons.kotlinmissingcode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentOne : Fragment() {

    override fun onAttach(context: Context) {
        Log.d("fragment_life_cycle", "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("fragment_life_cycle", "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("fragment_life_cycle", "onActivityCreated")
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