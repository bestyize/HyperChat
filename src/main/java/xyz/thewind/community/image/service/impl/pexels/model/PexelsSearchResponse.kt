package xyz.thewind.community.image.service.impl.pexels.model

import com.google.gson.annotations.SerializedName

class PexelsSearchResponse {
    var total: Int = 0
    var photos: MutableList<PexelsPhoto> = mutableListOf()
}

class PexelsPhoto {
    var id: Long = 0L
    var width: Int = 0
    var height: Int = 0
    @SerializedName("url")
    var pageUrl: String? = null
    @SerializedName("alt")
    var tags: String? = null
    var src: MutableMap<String, String> = mutableMapOf()
    fun previewImageUrl(): String? {
        return src["medium"]
    }
    fun imageUrl(): String? {
        return src["origin"]
    }
}