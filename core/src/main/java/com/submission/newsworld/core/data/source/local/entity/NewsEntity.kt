package com.submission.newsworld.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsId :Int,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,

    @ColumnInfo(name = "link")
    var link: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "pubDate")
    var pubDate: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)