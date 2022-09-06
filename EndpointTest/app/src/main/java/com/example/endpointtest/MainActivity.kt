package com.example.endpointtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    fun getData(){
        val  retrofitClient = NetworkUtils.getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object   : Callback<List<Posts>> {

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach{
                    textViewBody.text = textViewBody.text.toString().plus(it.body)
                    textViewId.text = textViewId.text.toString().plus(it.id)
                    textViewUserId.text = textViewUserId.text.toString().plus(it.userId)
                    textViewTitle.text = textViewTitle.text.toString().plus(it.title)

                }
            }
        })
    }
}