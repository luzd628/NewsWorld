package com.submission.newsworld.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: Any
)

data class PostsItem(

    @field:SerializedName("newsId")
    val newsId: Int,

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("pubDate")
    val pubDate: String
)

data class Data(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("posts")
    val posts: List<PostsItem>
)

