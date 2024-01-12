package com.yash.musicapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder().
                baseUrl("https://deezerdevs-deezer.p.rapidapi.com/").
                addConverterFactory(GsonConverterFactory.create()).build().
                create(apiInterface::class.java)

        val retroData = retrofitBuilder.getData("eminem")

        rv.layoutManager = LinearLayoutManager(this)

        retroData.enqueue(object : Callback<AllData?> {
            override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                val s = response.body()?.data!!
                val adapter = CustomAdapter(s)
                rv.adapter = adapter
            }

            override fun onFailure(call: Call<AllData?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"API Not Working",Toast.LENGTH_LONG).show();
            }
        })
    }
}