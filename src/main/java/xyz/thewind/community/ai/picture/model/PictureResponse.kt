package xyz.thewind.community.ai.picture.model

class PictureResponse {
    var code: Int = -1
    var msg: String? = null
    var data: List<PictureItem> = mutableListOf()
}