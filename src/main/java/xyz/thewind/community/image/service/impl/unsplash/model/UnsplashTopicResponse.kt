package xyz.thewind.community.image.service.impl.unsplash.model

import com.google.gson.annotations.SerializedName

class UnsplashTopicResponse {
    var results: MutableList<UnsplashImage> = mutableListOf()
}

class UnsplashImage{
    var id: String? = null
    var width: Int = 0
    var height: Int = 0
    @SerializedName("alt_description")
    var altDescription: String? = null
    var urls: UnsplashUrls? = null
    var links: UnsplashLinks? = null
    var likes: Int = 0

}

class UnsplashUrls {
    var raw: String? = null
    var regular: String? = null
    var full: String? = null
    var small: String? = null
}

class UnsplashLinks {
    var html: String? = null
}