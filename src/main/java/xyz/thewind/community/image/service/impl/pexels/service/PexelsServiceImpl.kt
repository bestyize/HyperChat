package xyz.thewind.community.image.service.impl.pexels.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.pexels.model.PexelsSearchResponse
import xyz.thewind.community.image.service.impl.pexels.model.PexelsWebSearchResponse
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
        val api = "https://www.pexels.com/zh-cn/api/v3/search/photos?page=$page&per_page=$num&query=" + keyword.urlEncode()
        val res = mutableListOf<ImageInfo>()
        try {
            val content = HttpUtil.get(api, mapOf(
                "secret-key" to "H2jk9uKnhRmL6WPwh89zBezWvr"
            ))
            val resp = GsonUtil.fromJson(content, PexelsWebSearchResponse::class.java)
            resp?:return res
            resp.data.forEach {
                res.add(ImageInfo().apply {
                    this.src = ImageSrc.PEXELS.src
                    this.id = it.id.toString()
                    this.imageUrl = it.attributes.imageUrl()
                    this.previewImageUrl = it.attributes.previewImageUrl()
                    this.width = it.attributes.width
                    this.height = it.attributes.height
                    this.tags = it.attributes.tags
                    this.pageUrl = it.attributes.pageUrl()
                })
            }

        } catch (_: Exception) {

        }
        return res
    }
}