package xyz.thewind.community.pixbay.model

import com.google.gson.annotations.SerializedName

class PixbayImageSearchResponse {
    var total: Int = 0
    @SerializedName("hits")
    var list: MutableList<PixbayImage> = mutableListOf()
}

class PixbayImage {
    var id: Long = 0L
    @SerializedName("pageURL")
    var pageUrl: String? = null
    var type: String = "photo"
    var tags: String = "tags"
    @SerializedName("webformatURL")
    var previewImageUrl: String? = null
    @SerializedName("largeImageURL")
    var imageUrl: String? = null
    @SerializedName("imageWidth")
    var width: Int = 0
    @SerializedName("imageHeight")
    var height: Int = 0
    @SerializedName("imageSize")
    var size: Long = 0
    var views: Long = 0
    var downloads: Long = 0
    var collections: Long = 0
    var comments: Long = 0
}