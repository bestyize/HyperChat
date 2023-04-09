package xyz.thewind.community.ai.picture.datasource.impl.adobe.model

import com.google.gson.annotations.SerializedName

class StyleImageItem {
    var id: String? = null
    var title: String? = null
    @SerializedName("anchor_prompt")
    var anchorPrompt: String? = null
    @SerializedName("style_prompt")
    var stylePrompt: String? = null
    var thumbnail: ThumbnailStyle? = null
}

class ThumbnailStyle {
    var url: String? = null
        get() {
            return if (field.isNullOrBlank()) "" else "https://clio-assets.adobe.com/clio-playground/image-styles/v4/en-US/$field"
        }
}