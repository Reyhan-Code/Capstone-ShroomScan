package com.dicoding.capstone.remote.database.fungus

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "fungus_db")
@Parcelize
class FungusEntity (

    @ColumnInfo(name = "id")

    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "jenis")
    var jenis: String? = null,

    @ColumnInfo(name = "deskripsi")
    var deskripsi: String? = null,

    @ColumnInfo(name = "media_tanam")
    var media_tanam: String? = null,

    @ColumnInfo(name = "gambar1")
    var gambar1: String? = null,

    @ColumnInfo(name = "gambar2")
    var gambar2: String? = null,

    @ColumnInfo(name = "gambar3")
    var gambar3: String? = null,
): Parcelable
