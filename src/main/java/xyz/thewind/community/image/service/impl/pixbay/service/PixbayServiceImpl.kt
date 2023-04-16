package xyz.thewind.community.image.service.impl.pixbay.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.pixbay.model.PixbaySearchResponse
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil
import xyz.thewind.torrent.datasource.def.urlEncode

class PixbayServiceImpl: ImageService {
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        return doSearch(keyword, page, num)
    }

    private fun doSearch(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val res = mutableListOf<ImageInfo>()
        val api = "https://pixabay.com/api/?key=35359169-d0387b5efe4184e8a51381124&lang=zh&q=${keyword.urlEncode()}&page=$page&per_page=$num"
        try {
            val content = HttpUtil.get(api)
            val resp = GsonUtil.fromJson(content, PixbaySearchResponse::class.java)
            resp.list.forEach {
                res.add(ImageInfo().apply {
                    this.src = ImageSrc.PIXBAY.src
                    this.id = it.id.toString()
                    this.imageUrl = it.imageUrl
                    this.previewImageUrl = it.previewImageUrl
                    this.width = it.width
                    this.height = it.height
                    this.type = it.type
                    this.tags = it.tags
                    this.pageUrl = it.pageUrl
                    this.views = it.views
                    this.downloads = it.downloads
                    this.collections = it.collections
                    this.likes = it.likes
                    this.comments = it.comments
                })
            }
        } catch (_: Exception) {}
        return res
    }
}