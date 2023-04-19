package xyz.thewind.community.image.service.impl.konachan.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.konachan.parser.KonaChanParser

class KonaChanServiceImpl : ImageService{
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        return KonaChanParser.search(keyword, page)
    }

    override fun recommendTabs(): MutableList<ImageRecommendTab> {
        val resp = mutableListOf<ImageRecommendTab>()
        val  src = ImageSrc.KONACHAN.src
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "推荐"
            this.query = ""
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "元神"
            this.query = "genshin_impact"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "穿衣"
            this.query = "dress"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "天空"
            this.query = "sky"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "原生"
            this.query = "original"
        })

        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "校服"
            this.query = "school_uniform"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "breasts"
            this.query = "breasts"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "长发"
            this.query = "long_hair"
        })
        resp.add(ImageRecommendTab().apply {
            this.src = src
            this.title = "red_eyes"
            this.query = "red_eyes"
        })

        return resp
    }
}