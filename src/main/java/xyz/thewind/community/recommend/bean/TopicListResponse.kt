package xyz.thewind.community.recommend.bean

class TopicListResponse {
    var code = -1
    var msg: String? = null
    var data: TopicCardData = TopicCardData()
}

class TopicCardData {
    var aspectRadio: Float = 0.75f
    var columnCount: Int = 1
    var cardList: MutableList<TopicCardItem> = mutableListOf()
}

class TopicCardItem {
    var topicId: Int = 0
    var title: String? = null
    var desc: String? = null
    var leftBottomText: String? = null
    var leftBottomIcon: String? = null
    var cover: String? = null
    var topicType: Int = 0
    var actionUrl: String? = null
}


enum class TopicId(val topic: Int) {
    WEB(0),
    PIXBAY(1),
    PEXELS(2),
    MAGNET(3),
    KONACHAN(4),
    WALLHAVEN(5)
}