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
                this.leftBottomText = "9999+热度"
                this.cover = "https://cdn.pixabay.com/photo/2022/03/31/08/47/moon-7102510_960_720.jpg"
                this.actionUrl = "https://thewind.xyz"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.PEXELS.topic
                this.title = "Pexels"
                this.desc = "超高质量的图片免费下"
                this.leftBottomText = "8000+热度"
                this.cover = "https://s.cn.bing.net/th?id=OHR.KiteDay_ZH-CN7813901578_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&qlt=50"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.PIXBAY.topic
                this.title = "Pixbay"
                this.desc = "来自Pixbay的百万精美图片"
                this.leftBottomText = "1000+热度"
                this.cover = "https://cdn.pixabay.com/photo/2013/04/03/21/25/flower-100263_960_720.jpg"
            },
            TopicCardItem().apply {
                this.topicId = TopicId.MAGNET.topic
                this.title = "磁力社"
                this.desc = "搜索下载种子"
                this.leftBottomText = "2w+热度"
                this.cover = "https://cdn.pixabay.com/photo/2022/09/22/08/41/forest-7471935_960_720.jpg"
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