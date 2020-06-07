package com.br.apps.awequotes.data.network.model

import com.google.gson.annotations.SerializedName

data class QuotesResult(
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("_id") val _id: String,
    @SerializedName("content") val content: String,
    @SerializedName("author") val author: String,
    @SerializedName("length") val length: Int
)