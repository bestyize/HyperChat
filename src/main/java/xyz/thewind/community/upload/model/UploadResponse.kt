package xyz.thewind.community.upload.model

class UploadResponse {
    var code: Int = -1
    var msg: String = ""
    var list: MutableList<String> = mutableListOf()
}