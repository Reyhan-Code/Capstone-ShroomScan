package com.dicoding.capstone.remote.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "recipe")
@Parcelize
class FungusEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name_recipe")
    var name_recipe: String? = null,

    @ColumnInfo(name = "ingredients")
    var ingredients: String? = null,

    @ColumnInfo(name = "howToMake")
    var howToMake: String? = null,

    @ColumnInfo(name = "photoUrl")
    var photoUrl: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
) : Parcelable