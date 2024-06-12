package com.dicoding.capstone.remote.database.fungus

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
    suspend fun insert(fungus: List<FungusEntity>)

    @Update
    suspend fun update(fungus: FungusEntity)

    @Delete
    suspend fun delete(fungus: FungusEntity)

    @Query("SELECT * FROM fungus_db")
    fun getAllFungus(): LiveData<List<FungusEntity>>


    @Query("DELETE FROM fungus_db")
    suspend fun deleteAll()

    @Query("SELECT * FROM fungus_db WHERE nama LIKE '%' || :query || '%'")
    fun searchFungus(query: String): LiveData<List<FungusEntity>>
}