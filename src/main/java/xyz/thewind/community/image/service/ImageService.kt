package xyz.thewind.community.image.service

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageRecommendTab

interface ImageService {

    fun search(keyword: String, page: Int, num: Int) : MutableList<ImageInfo>

    fun recommendTabs(): MutableList<ImageRecommendTab>

}