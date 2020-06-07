package com.br.apps.awequotes.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_table")
data class Quotes(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "is_fav") var isFav: Boolean = false
)
