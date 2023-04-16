package xyz.thewind.community.image.service.impl.pexels.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.pexels.model.PexelsSearchResponse
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil
import xyz.thewind.torrent.datasource.def.urlEncode

class PexelsServiceImpl : ImageService {
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val res = mutableListOf<ImageInfo>()
        if (keyword.isBlank()) {
            return res
        }
        return doSearch(keyword, page, num)

    }


    private fun doSearch(keyword: String, page: Int, num: Int): MutableList<ImageInfo>  {
        val api = "https://api.pexels.com/v1/search?query=${keyword.urlEncode()}&per_page=$num&page=$page&locale=zh"
        val res = mutableListOf<ImageInfo>()
        try {
            val content = HttpUtil.get(api, mapOf(
                "Authorization" to "H2jk9uKnhRmL6WPwh89zBezWvr"
            ))
            val resp = GsonUtil.fromJson(content, PexelsSearchResponse::class.java)
            resp?:return res
            resp.photos.forEach {
                res.add(ImageInfo().apply {
                    this.src = ImageSrc.PEXELS.src
                    this.id = it.id.toString()
                    this.imageUrl = it.imageUrl()
                    this.previewImageUrl = it.previewImageUrl()
                    this.width = it.width
                    this.height = it.height
                    this.tags = it.tags
                    this.pageUrl = it.pageUrl
                })
            }

        } catch (_: Exception) {

        }
        return res
    }
}