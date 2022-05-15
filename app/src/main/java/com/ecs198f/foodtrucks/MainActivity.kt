package com.ecs198f.foodtrucks

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.ecs198f.foodtrucks.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    lateinit var foodTruckDao: FoodTruckDao
    lateinit var foodItemDao: FoodItemDao
    var currentNetwork: Network? = null

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalDateTime {
                return LocalDateTime.parse(json!!.asString)
            }
        })
        .create()

    val foodTruckService: FoodTruckService = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(FoodTruckService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val connMgr = ContextCompat.getSystemService(
            applicationContext,
            ConnectivityManager::class.java
        )

        currentNetwork = connMgr?.activeNetwork

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "local-db")
            .build()
        foodTruckDao = db.FoodTruckDao()
        foodItemDao = db.FoodItemDao()

        setContentView(binding.root)
        title = "Food Trucks"
    }
}