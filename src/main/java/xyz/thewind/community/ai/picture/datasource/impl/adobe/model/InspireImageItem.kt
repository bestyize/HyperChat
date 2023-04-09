package xyz.thewind.community.ai.picture.datasource.impl.adobe.model

class InspireImageItem {
    var title: String? = null
    var prompt: String? = null
    var style: String? = null
    var thumbnail: Thumbnail? = null
}

class Thumbnail {
    var url: String? = null
        get() {
            return if (field.isNullOrBlank()) "" else "https://clio-assets.adobe.com/clio-playground/image-inspirations/v4/en-US/$field"
        }
}