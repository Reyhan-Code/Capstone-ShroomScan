package com.dicoding.capstone.remote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FungusEntity::class], version = 1, exportSchema = false)
abstract class FungusDb : RoomDatabase() {
    abstract fun fungusDao(): FungusDao

    companion object {
        @Volatile
        private var instance: FungusDb? = null
        fun getInstance(context: Context): FungusDb =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FungusDb::class.java, "fungus_database.db"
                ).build().also { instance = it }
            }
    }
}