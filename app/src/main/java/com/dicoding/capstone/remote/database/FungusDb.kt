package com.dicoding.capstone.remote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.capstone.remote.database.fungus.FungusDao
import com.dicoding.capstone.remote.database.fungus.FungusEntity
import com.dicoding.capstone.remote.database.recipe.RecipeDao
import com.dicoding.capstone.remote.database.recipe.RecipeEntity

@Database(entities = [RecipeEntity::class, FungusEntity::class], version = 1, exportSchema = false)
abstract class FungusDb : RoomDatabase() {
    abstract fun fungusDao(): FungusDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: FungusDb? = null
        fun getInstance(context: Context): FungusDb =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FungusDb::class.java, "fungus_database.db"
                ).build()
                    .also { instance = it }
            }
    }
}