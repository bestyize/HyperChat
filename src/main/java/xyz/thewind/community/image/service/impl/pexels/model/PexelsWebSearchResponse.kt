package xyz.thewind.community.image.service.impl.pexels.model

import com.google.gson.annotations.SerializedName

class PexelsWebSearchResponse {
    var data: MutableList<ImageDetail> = mutableListOf()
}

class ImageDetail {
    var id: String? = null
    var type: String? = null
    var attributes: ImageAttr = ImageAttr()

}

class ImageAttr {
    var id: Long = 0L
    var width: Int = 0
    var height: Int = 0
    @SerializedName("alt")
    var tags: String? = null

    var image: MutableMap<String, String> = mutableMapOf()

    fun pageUrl(): String?  {
        return "https://www.pexels.com/zh-cn/photo/$id/"
    }

    fun previewImageUrl(): String? {
        return image["medium"]
    }
    fun imageUrl(): String? {
        return image["download_link"]
    }
}