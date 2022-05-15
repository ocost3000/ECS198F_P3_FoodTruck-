package com.ecs198f.foodtrucks

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ecs198f.foodtrucks.models.FoodItem
import com.ecs198f.foodtrucks.models.FoodTruck
import java.time.LocalDateTime

@Database(entities = [FoodTruck::class, FoodItem::class], version = 1)
@TypeConverters(AppDatabase.LocalDateTimeTypeConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun FoodTruckDao(): FoodTruckDao
    abstract fun FoodItemDao(): FoodItemDao

    class LocalDateTimeTypeConverters {
        @TypeConverter
        fun fromString(string: String): LocalDateTime = LocalDateTime.parse(string)
        @TypeConverter
        fun toString(localDateTime: LocalDateTime): String = localDateTime.toString()
    }
}