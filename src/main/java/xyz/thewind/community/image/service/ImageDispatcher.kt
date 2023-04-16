package xyz.thewind.community.image.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.impl.pexels.service.PexelsServiceImpl
import xyz.thewind.community.image.service.impl.pixbay.service.PixbayServiceImpl

object ImageDispatcher {

    fun search(keyword: String?, page: Int?, num: Int?, src: Int?): MutableList<ImageInfo>{
        val res = mutableListOf<ImageInfo>()
        if (keyword == null || page == null || num == null || src == null) {
            return res
        }
        val service = when(src) {
            ImageSrc.PEXELS.src -> PexelsServiceImpl()
            ImageSrc.PIXBAY.src -> PixbayServiceImpl()
            else -> PexelsServiceImpl()
        }
        return service.search(keyword, page, num)

    }

    fun recommendTabs(src: Int): MutableList<ImageRecommendTab> {
        val resp = mutableListOf<ImageRecommendTab>()
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "推荐"
            this.query = "精彩"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "壁纸"
            this.query = "壁纸"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "城市"
            this.query = "城市"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "艺术"
            this.query = "艺术"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "美女"
            this.query = "美女"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "风景"
            this.query = "风景"
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

}