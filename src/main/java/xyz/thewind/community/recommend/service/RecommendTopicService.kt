package xyz.thewind.community.recommend.service

import xyz.thewind.community.recommend.bean.TopicCardData
import xyz.thewind.community.recommend.bean.TopicCardItem
import xyz.thewind.community.recommend.bean.TopicId
import xyz.thewind.community.recommend.bean.TopicListResponse


object RecommendTopicServiceHelper {
    fun loadDefaultRecommendTopicList(): TopicListResponse {
        val list = listOf(
            TopicCardItem().apply {
                this.title = "无名音乐"
                this.desc = "无损音乐在线下"
                this.leftBottomText = ""
                this.cover = "https://cdn.pixabay.com/photo/2022/03/31/08/47/moon-7102510_960_720.jpg"
                this.actionUrl = "https://thewind.xyz"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.PEXELS.topic
                this.title = "Pexels"
                this.desc = "超高质量的图片免费下"
                this.leftBottomText = ""
                this.cover = "https://thewind.xyz/community/api/picture/res/pexel_cover.jpg"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.PIXBAY.topic
                this.title = "Pixbay"
                this.desc = "来自Pixbay的百万精美图片"
                this.leftBottomText = ""
                this.cover = "https://thewind.xyz/community/api/picture/res/pixbay_cover.jpg"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.MAGNET.topic
                this.title = "磁力社"
                this.desc = "搜索下载种子"
                this.leftBottomText = ""
                this.cover = "https://thewind.xyz/community/api/picture/res/cilishe_cover.jpg"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.KONACHAN.topic
                this.title = "Kona"
                this.desc = "超多动漫美图！"
                this.leftBottomText = ""
                this.cover = "https://thewind.xyz/community/api/picture/res/kona_cover.jpg"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.WALLHAVEN.topic
                this.title = "壁纸天堂"
                this.desc = "高清壁纸的天堂"
                this.leftBottomText = ""
                this.cover = "https://thewind.xyz/community/api/picture/res/wallhaven_cover.jpg"
            }
        )

        val data = TopicCardData().apply {
            this.aspectRadio = 1.6f
            this.cardList.addAll(list)
            this.columnCount = 2

        }

        return TopicListResponse().apply {
            this.code = 0
            this.data = data

        }
    }

}