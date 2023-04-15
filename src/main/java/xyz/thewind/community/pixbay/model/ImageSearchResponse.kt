package xyz.thewind.community.pixbay.model

/**
 * @author: read
 * @date: 2023/4/15 下午8:49
 * @description:
 */
class ImageSearchResponse {
    var code: Int = -1
    var msg: String? = null
    var data: MutableList<ImageSearchData> = mutableListOf()
}