package com.example.mybestfriends

import MainAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.jar.Attributes
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var pageNumber = 1
    var pageSeed:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pageSeed = Random.nextInt(100)

        rv_main.layoutManager = LinearLayoutManager(this)

        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            pageSeed = Random.nextInt(100)
            pageNumber = 1
            fetchJson()
            itemsswipetorefresh.isRefreshing = false
        }

        button_Next.setOnClickListener(){
            pageNumber += 1
            if (pageNumber>3) pageNumber = 1
            fetchJson()
        }

        button_Prew.setOnClickListener(){
            pageNumber -= 1
            if (pageNumber<1) pageNumber = 3
            fetchJson()
        }

        fetchJson()

    }

    private fun fetchJson(){
        val url = "https://randomuser.me/api/?page=$pageNumber&results=10&seed=$pageSeed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                println(body)

                val gson = GsonBuilder().create()

                val phoneBook = gson.fromJson(body, PhoneBook::class.java)



                runOnUiThread{
                    rv_main.adapter = MainAdapter(phoneBook)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("FAILD")
            }
        })
    }

}

class PhoneBook(val results: List<Person>)

class Person (val name: Name, val email: String, val phone: String, val picture: Pictures , val gender: String, val location: Location, val nat: String)

class Street(val number:String, val name:String){
    override fun toString(): String {
        return "$name $number"
    }
}

class Location (val street: Street, val city: String, val state: String, val postcode: String){

    override fun toString(): String {
        val fullStret = street.toString()
        return "$fullStret, $city, $state, $postcode"
    }
}

class Pictures (val large: String, val medium: String, val thumbnail: String)

class Name (val title: String, val first: String, val last: String) {
    override fun toString(): String {
        return "$title $first $last"
    }
}
