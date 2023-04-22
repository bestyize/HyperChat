package xyz.thewind.community.image.service.impl.hubble.parser


class HubbleResponse {
    val list: MutableList<HubbleData> = mutableListOf()
}

class HubbleData {
    var id: String? = null
    var title: String? = null
    var width: Int = 0
    var height: Int = 0
    var src: String? = null
    var url: String? = null
    var potw: String? = null
}