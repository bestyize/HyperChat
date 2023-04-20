package xyz.thewind.community.image.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.impl.konachan.service.KonaChanServiceImpl
import xyz.thewind.community.image.service.impl.pexels.service.PexelsServiceImpl
import xyz.thewind.community.image.service.impl.pixbay.service.PixbayServiceImpl
import xyz.thewind.community.image.service.impl.wallhaven.service.WallHavenServiceImpl

object ImageDispatcher {

    fun search(keyword: String?, page: Int?, num: Int?, src: Int?): MutableList<ImageInfo>{
        val res = mutableListOf<ImageInfo>()
        if (keyword == null || page == null || num == null || src == null) {
            return res
        }
        val service = when(src) {
            ImageSrc.PEXELS.src -> PexelsServiceImpl()
            ImageSrc.PIXBAY.src -> PixbayServiceImpl()
            ImageSrc.KONACHAN.src -> KonaChanServiceImpl()
            ImageSrc.WALLHAVEN.src -> WallHavenServiceImpl()
            else -> PexelsServiceImpl()
        }
        return service.search(keyword, page, num)

    }

    fun recommendTabs(src: Int): MutableList<ImageRecommendTab> {
        val service = when(src) {
            ImageSrc.PEXELS.src -> PexelsServiceImpl()
            ImageSrc.PIXBAY.src -> PixbayServiceImpl()
            ImageSrc.KONACHAN.src -> KonaChanServiceImpl()
            ImageSrc.WALLHAVEN.src -> WallHavenServiceImpl()
            else -> PexelsServiceImpl()
        }
        return service.recommendTabs()
    }

}