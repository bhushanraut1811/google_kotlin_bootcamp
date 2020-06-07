package com.br.apps.awequotes.data.network.model

import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("lastItemIndex") val lastItemIndex: Int,
    @SerializedName("results") val results: List<QuotesResult>
)