package com.example.randompet

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var petImageUrl = ""
    private fun getDogImageURL() {
        val client = AsyncHttpClient()
        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful$json")
                petImageUrl = json.jsonObject.getString("message")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }

    private fun getCatImageURL() {
        val client = AsyncHttpClient()
        client["https://api.thecatapi.com/v1/images/search", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Cat", "response successful$json")
                petImageUrl = json.jsonArray.getJSONObject(0).getString("url")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Cat Error", errorResponse)
            }
        }]
    }

    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            var randNum = Random.nextInt(2)
            if (randNum == 1) {
                getDogImageURL()
            } else {
                getCatImageURL()
            }

            Glide.with(this)
                .load(petImageUrl)
                .fitCenter()
                .into(imageView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.petButton)
        var imageView = findViewById<ImageView>(R.id.petImage)

        getDogImageURL()
        getNextImage(button, imageView)
        Log.d("petImageUrl", "pet image url set")
    }
}