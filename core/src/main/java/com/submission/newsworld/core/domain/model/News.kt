package com.submission.newsworld.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (
    val newsId:Int,
    val thumbnail: String,
    val link: String,
    val description: String,
    val title: String,
    val pubDate: String,
    val isFavorite: Boolean
):Parcelable