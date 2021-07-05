package com.yoochangwons.kotlinmissingcode

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.yoochangwons.kotlinmissingcode.databinding.ActivityPhoneBookBinding

class PhoneBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val phoneBookArrayList = ArrayList<PhoneBookList>()
        for (i in 0 until 30) {
            phoneBookArrayList.add(PhoneBookList("$i 번째 사람"))
        }

        val inflater = LayoutInflater.from(this)

        for (i in phoneBookArrayList.indices) {
            val itemView = inflater.inflate(R.layout.item_phone_book, null)
            val itemPhoneBookImage = itemView.findViewById<ImageView>(R.id.phone_book_image)
            val itemPhoneBookName = itemView.findViewById<TextView>(R.id.phone_book_name)

            Glide.with(itemView)
                .load("https://file.mk.co.kr/meet/neds/2021/04/image_readtop_2021_330747_16177500644599916.jpg")
                .into(itemPhoneBookImage)

            itemPhoneBookName.text = phoneBookArrayList[i].phoneBookName
            binding.phoneBookContainer.addView(itemView)
        }
    }
}

data class PhoneBookList(val phoneBookName: String)
