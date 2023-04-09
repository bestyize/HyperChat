package xyz.thewind.community.ai.picture.datasource.impl.adobe

import xyz.thewind.community.ai.picture.datasource.impl.adobe.model.AdobeImageInspirationResponse
import xyz.thewind.community.ai.picture.datasource.impl.adobe.model.AdobeImageStyleResponse
import xyz.thewind.community.ai.picture.model.PictureItem
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil

object AdobePictureService {

    fun requestImageInspirations(): List<PictureItem> {
        if (lastRequestTime + 86400000L < System.currentTimeMillis() && inspirationList.isNotEmpty()) {
            return inspirationList
        }
        val respText = HttpUtil.get(IMAGE_INSPIRATIONS_URL)
        try {
            val resp = GsonUtil.fromJson(respText, AdobeImageInspirationResponse::class.java)
            return resp.inspirations.map { PictureItem().apply {
                this.title = it.title
                this.prompt = it.prompt
                this.style = it.style
                this.url = it.thumbnail?.url
            } }
        } catch (_: Exception) {

        }
        return listOf()
    }

    fun requestImageStyles(): List<PictureItem> {
        if (lastRequestTime + 86400000L < System.currentTimeMillis() && inspirationList.isNotEmpty()) {
            return inspirationList
        }
        val respText = HttpUtil.get(IMAGE_STYLE_URL)
        try {
            val resp = GsonUtil.fromJson(respText, AdobeImageStyleResponse::class.java)
            return resp.styles.map { PictureItem().apply {
                this.title = it.title
                this.prompt = it.stylePrompt + it.anchorPrompt
                this.style = it.id
                this.url = it.thumbnail?.url
            } }
        } catch (_: Exception) {

        }
        return listOf()
    }

    private var lastRequestTime = 0L
    private var inspirationList = mutableListOf<PictureItem>()

    private var lastRequestTimeStyle = 0L
    private var styleList = mutableListOf<PictureItem>()

    private const val IMAGE_INSPIRATIONS_URL = "https://clio-assets.adobe.com/clio-playground/image-inspirations/v4/en-US/content.json"

    private const val IMAGE_STYLE_URL = "https://clio-assets.adobe.com/clio-playground/image-styles/v4/en-US/content.json"
}


