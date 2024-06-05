package com.dicoding.capstone.remote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FungusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: List<FungusEntity>)

    @Update
    suspend fun update(recipe: FungusEntity)

    @Delete
    suspend fun delete(recipe: FungusEntity)

    @Query("SELECT * FROM recipe")
    fun getRecipe(): LiveData<List<FungusEntity>>

    @Query("SELECT * FROM recipe WHERE isFavorite = 1")
    fun getFavoriteRecipe(): LiveData<List<FungusEntity>>

    @Query("DELETE FROM recipe WHERE isFavorite = 0")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM recipe WHERE id = :id AND isFavorite = 1)")
    suspend fun isFavorite(id: String): Boolean
}