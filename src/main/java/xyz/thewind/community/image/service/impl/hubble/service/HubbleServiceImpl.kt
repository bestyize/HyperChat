package xyz.thewind.community.image.service.impl.hubble.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageService
import xyz.thewind.community.image.service.impl.hubble.parser.HubbleParser

class HubbleServiceImpl : ImageService {
    override fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        return HubbleParser.search(keyword, page, num)
    }

    override fun recommendTabs(): MutableList<ImageRecommendTab> {
        return mutableListOf(
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "排行榜"
                this.query = "/images/viewall/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "年度图像"
                this.query = "/images/archive/category/anniversary/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "星云"
                this.query = "/images/archive/category/nebulae/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "星系"
                this.query = "/images/archive/category/galaxies/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "超星系团"
                this.query = "/images/archive/category/cosmology/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "系外行星"
                this.query = "/images/archive/category/exoplanets/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "星系团"
                this.query = "/images/archive/category/starclusters/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "星星"
                this.query = "/images/archive/category/stars/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "黑洞"
                this.query = "/images/archive/category/blackholes/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "太阳系"
                this.query = "/images/archive/category/solarsystem/page/"
                this.num = 20
                this.page = 1
            },
            ImageRecommendTab().apply {
                this.src = ImageSrc.HUBBLE.src
                this.title = "航空器"
                this.query = "/images/archive/category/mission/page/"
                this.num = 20
                this.page = 1
            }
        )
    }
}