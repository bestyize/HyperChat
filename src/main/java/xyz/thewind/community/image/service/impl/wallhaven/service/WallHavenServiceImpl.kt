package xyz.thewind.community.image.service.impl.wallhaven.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.wallhaven.parser.WallHavenParser

class WallHavenServiceImpl : ImageService{
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        return WallHavenParser.search(keyword, page, num)
    }

    override fun recommendTabs(): MutableList<ImageRecommendTab> {
        return mutableListOf(
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "categories=111&purity=111&topRange=1y&sorting=toplist&order=desc&ai_art_filter=0"
                this.title = "推荐"
                this.page = 1
            },//categories=111&purity=111&ratios=landscape&sorting=toplist&order=desc&ai_art_filter=1
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "categories=111&purity=111&ratios=portrait&topRange=1y&sorting=toplist&order=desc&ai_art_filter=1"
                this.title = "手机壁纸"
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "categories=111&purity=111&ratios=landscape&sorting=toplist&order=desc&ai_art_filter=1"
                this.title = "电脑壁纸"
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "q=id%3A110383&categories=110&purity=100&sorting=favorites&order=desc&ai_art_filter=1"
                this.title = "动漫"
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "q=id%3A37&categories=110&purity=110&sorting=random&order=desc&ai_art_filter=1"
                this.title = "自然"
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.WALLHAVEN.src
                this.query = "q=id%3A37&categories=110&purity=110&sorting=random&order=desc&ai_art_filter=1"
                this.title = "随机"
                this.page = 1
            }
        )
    }
}