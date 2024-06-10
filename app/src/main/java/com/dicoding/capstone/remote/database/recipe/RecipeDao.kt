package com.dicoding.capstone.remote.database.recipe

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.capstone.remote.database.recipe.RecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: List<RecipeEntity>)

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe")
    fun getRecipe(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE isFavorite = 1")
    fun getFavoriteRecipe(): LiveData<List<RecipeEntity>>

    @Query("DELETE FROM recipe WHERE isFavorite = 0")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM recipe WHERE id = :id AND isFavorite = 1)")
    suspend fun isFavorite(id: String): Boolean

    @Query("SELECT * FROM recipe WHERE name_recipe LIKE '%' || :query || '%'")
    fun searchRecipe(query: String): LiveData<List<RecipeEntity>>
}