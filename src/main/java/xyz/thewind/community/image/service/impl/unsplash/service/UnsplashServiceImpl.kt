package xyz.thewind.community.image.service.impl.unsplash.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.unsplash.model.UnsplashTopicResponse
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil
import xyz.thewind.torrent.datasource.def.urlEncode

class UnsplashServiceImpl: ImageService {
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        return searchParser(keyword, page, num)
    }

    override fun recommendTabs(): MutableList<ImageRecommendTab> {
        return mutableListOf(
            ImageRecommendTab().apply {
                this.title = "推荐"
                this.query = "recommend"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 12
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "自然"
                this.query = "/napi/topics/nature/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 12
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "旅行"
                this.query = "/napi/topics/travel/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "建筑&室内"
                this.query = "/napi/topics/architecture-interior/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "街道摄影"
                this.query = "/napi/topics/street-photography/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "电影质感"
                this.query = "/napi/topics/film/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "运动"
                this.query = "/napi/topics/athletics/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.title = "文化艺术"
                this.query = "/napi/topics/arts-culture/photos?xp=search-quality-boosting%3Acontrol"
                this.src = ImageSrc.UNSPLASH.src
                this.num = 20
                this.page = 1
            }
        )
    }


    private fun searchParser(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val list: MutableList<ImageInfo> = mutableListOf()
        val data = if (keyword.contains("/napi/topics/")) {
            val api = "https://unsplash.com$keyword&page=$page&per_page=$num"
            val content = HttpUtil.get(api)
            if (content.isNullOrBlank()) return list
            "{\"results\":$content}"
        } else if (keyword == "recommend") {
            val api = "https://unsplash.com/napi/photos?per_page=$num&page=$page&xp=search-quality-boosting%3Acontrol"
            val content = HttpUtil.get(api)
            if (content.isNullOrBlank()) return list
            "{\"results\":$content}"
        } else {
            val api = "https://unsplash.com/napi/search/photos?query=${keyword.urlEncode()}&per_page=$num&page=$page&xp=search-quality-boosting%3Acontrol"
            HttpUtil.get(api)
        }
        try {
            val resp: UnsplashTopicResponse? = GsonUtil.fromJson(data, UnsplashTopicResponse::class.java)
            resp?.results?.forEach {
                list.add(ImageInfo().apply {
                    this.src = ImageSrc.UNSPLASH.src
                    this.id = it.id
                    this.originImageUrl = it.urls?.raw + "&fm=png"
                    this.imageUrl = it.urls?.full + "&fm=png"
                    this.previewImageUrl = it.urls?.regular + "&fm=png"
                    this.width = it.width
                    this.height = it.height
                    this.tags = it.altDescription
                    this.pageUrl = it.links?.html
                    this.likes = it.likes.toLong()
                })
            }
        } catch (_:Exception){}
        return list
    }
}