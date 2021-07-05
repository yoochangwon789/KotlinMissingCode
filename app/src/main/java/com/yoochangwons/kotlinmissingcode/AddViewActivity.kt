package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.yoochangwons.kotlinmissingcode.databinding.ActivityAddViewBinding

class AddViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
        }

        val inflater = LayoutInflater.from(this)
        for (i in 0 until carList.size) {
            val itemView = inflater.inflate(R.layout.item_view, null)
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)

            carNameView.text = carList[i].name
            carEngineView.text = carList[i].engine
            binding.addViewContainer.addView(itemView)
        }
    }

}

class CarForList(val name: String, val engine: String) {}
