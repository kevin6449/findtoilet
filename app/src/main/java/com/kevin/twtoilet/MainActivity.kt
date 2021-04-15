package com.kevin.twtoilet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.kevin.twtoilet.Data.toiletData
import com.kevin.twtoilet.api.openDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var toiletDataResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toiletDataResult = findViewById(R.id.result_text)

        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(openDataService::class.java)
        val call = service.fetchToiletsList()
        call.enqueue(object : Callback<toiletData> {
            override fun onResponse(call: Call<toiletData>, response: Response<toiletData>) {
                if (response.code() == 200) {
                    val toiletResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            toiletResponse.records.get(0).City +
                            "\n" +
                            "Country: " +
                            toiletResponse.records.get(0).Country +
                            "\n" +
                            "Name: " +
                            toiletResponse.records.get(0).Name +
                            "\n" +
                            "Address: " +
                            toiletResponse.records.get(0).Address +
                            "\n" +
                            "Grade: " +
                            toiletResponse.records.get(0).Grade +
                            "\n" +
                            "Type2: " +
                            toiletResponse.records.get(0).Type2

                    toiletDataResult!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<toiletData>, t: Throwable) {
                toiletDataResult!!.text = t.message
            }
        })
    }

    companion object{
        val BaseUrl = "https://data.epa.gov.tw/api/v1/"

    }
}