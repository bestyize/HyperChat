package xyz.thewind.community.image.service.impl.pexels.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.pexels.model.PexelsWebSearchResponse
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil
import xyz.thewind.torrent.datasource.def.urlEncode
import java.text.SimpleDateFormat

class PexelsServiceImpl : ImageService {
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val res = mutableListOf<ImageInfo>()
        if (keyword.isBlank()) {
            return res
        }
        return doSearch(keyword, page, num)

    }

    override fun recommendTabs(): MutableList<ImageRecommendTab> {
        val resp = mutableListOf<ImageRecommendTab>()
        val src = ImageSrc.PEXELS.src
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "推荐"
            this.query = "recommend"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "壁纸"
            this.query = "手机壁纸"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "城市"
            this.query = "城市"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "风景"
            this.query = "风景"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "艺术"
            this.query = "艺术"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "孤独"
            this.query = "孤独"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "美女"
            this.query = "美女"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "创意"
            this.query = "创意"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "日落"
            this.query = "日落"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "歌手"
            this.query = "歌手"
        })
        return resp
    }


    private fun doSearch(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val timestamp = System.currentTimeMillis() - (page - 1) * 86400000L * 5
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
        val seed = formatter.format(timestamp).urlEncode()
        val sApi =
            "https://www.pexels.com/zh-cn/api/v3/search/photos?page=$page&per_page=$num&query=" + keyword.urlEncode()
        val rApi = "https://www.pexels.com/zh-cn/api/v2/feed?seed=$seed&per_page=12&seo_tags=true"
        val api = if (keyword == "recommend") rApi else sApi
        val res = mutableListOf<ImageInfo>()
        try {
            val content = HttpUtil.get(
                api, mapOf(
                    "secret-key" to "H2jk9uKnhRmL6WPwh89zBezWvr"
                )
            )
            val resp = GsonUtil.fromJson(content, PexelsWebSearchResponse::class.java)
            resp ?: return res
            resp.data.filter { it.type == "photo" }.forEach {
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