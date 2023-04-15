package xyz.thewind.community.pixbay.model

/**
 * @author: read
 * @date: 2023/4/15 下午8:29
 * @description:
 * https://pixabay.com/api/docs/
 */
class PixbayImageQuery() {
    var q: String? = null
    var lang: String = "zh"
    var page: Int = 1
    var num: Int = 20
    var imageType: String = "all"

}

enum class ImageType(val type: String) {
    // "all", "photo", "illustration", "vector"
    ALL("all"),
    PHOTO("photo"),
    ILLUSTRATION("illustration"),
    VECTOR("vector")
}