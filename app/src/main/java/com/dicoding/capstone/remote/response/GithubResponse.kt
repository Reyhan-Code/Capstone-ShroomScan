package com.dicoding.capstone.remote.response

import com.google.gson.annotations.SerializedName

data class GithubResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<ItemsItem>? = null
)


data class ItemsItem(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("bio")
    val bio: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    )