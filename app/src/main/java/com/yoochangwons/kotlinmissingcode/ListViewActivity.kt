package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.yoochangwons.kotlinmissingcode.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListViewBinding

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("$i 번째 자동차", "" + i + "순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, this)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this, "$carName $carEngine", Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(
    private val carForList: ArrayList<CarForList>,
    val context: Context
) : BaseAdapter() {

    override fun getCount(): Int {
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        return carForList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_view, null)
        val carNameText = view.findViewById<TextView>(R.id.car_name)
        val carEngineText = view.findViewById<TextView>(R.id.car_engine)

        carNameText.text = carForList[position].name
        carEngineText.text = carForList[position].engine

        return view
    }

}