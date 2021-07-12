package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.kotlinmissingcode.databinding.ActivityRetrofitRecyclerViewBinding
import com.yoochangwons.kotlinmissingcode.databinding.NetWorkItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val adapter = RetrofitRecyclerViewAdapter(body)
                    binding.retrofitRecyclerView.adapter = adapter
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(this@RetrofitRecyclerView)
                }
            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
            }
        })
    }
}

class RetrofitRecyclerViewAdapter(
    private var dataSet: ArrayList<PersonFromServer>?
) : RecyclerView.Adapter<RetrofitRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: NetWorkItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.net_work_item, viewGroup, false)

        return ViewHolder(NetWorkItemBinding.bind(view))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemBinding.dataId.text = "id : ${dataSet?.get(position)?.id}"
        viewHolder.itemBinding.dataName.text = "name : ${dataSet?.get(position)?.name}"
        viewHolder.itemBinding.dataAge.text = "age : ${dataSet?.get(position)?.age}"
        viewHolder.itemBinding.dataIntro.text = "intro : ${dataSet?.get(position)?.intro}"
    }

    override fun getItemCount() = dataSet!!.size

}