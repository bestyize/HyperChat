package xyz.thewind.community.image.service

import xyz.thewind.community.image.model.ImageInfo

interface ImageService {
    fun search(keyword: String, page: Int, num: Int) : MutableList<ImageInfo>
}