package xyz.thewind.community.image.service.impl.wallhaven.parser

import org.jsoup.Jsoup
import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.global.util.HttpUtil

object WallHavenParser {
    fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo> {
        val list = mutableListOf<ImageInfo>()
        try {
            val api = if (keyword.contains("sorting=")) "https://wallhaven.cc/search?$keyword&page=$page" else "https://wallhaven.cc/search?q=$keyword&categories=111&purity=111&topRange=1y&sorting=toplist&order=desc&ai_art_filter=1&page=$page"

            val content = HttpUtil.get(api)
            if (content.isEmpty()) return list
            val data = Jsoup.parse(content).body().getElementsByClass("thumb-listing-page")[0]
            val items = data.getElementsByTag("figure")
            items.forEach {
                try {
                    val id = it.attr("data-wallpaper-id")
                    val previewImageUrl = it.getElementsByTag("img")[0].attr("data-src")
                    val pageUrl = it.getElementsByClass("preview")[0].attr("href")
                    val whStr = it.getElementsByClass("wall-res")[0].text()
                    val w = whStr.replace(" ", "").split("x")[0].toInt()
                    val h = whStr.replace(" ", "").split("x")[1].toInt()
                    var imageUrl = previewImageUrl.replace("https://th.wallhaven.cc/", "https://w.wallhaven.cc/").replace("/small/", "/full/").replace("/${id}.", "/wallhaven-${id}.")
                    if (it.getElementsByClass("png").size > 0) {
                        imageUrl = imageUrl.replace(".jpg", ".png")
                    }
                    list.add(ImageInfo().apply {
                        this.src = ImageSrc.WALLHAVEN.src
                        this.id = id
                        this.imageUrl = imageUrl
                        this.previewImageUrl = previewImageUrl
                        this.previewWidth = 300
                        this.previewHeight = 200
                        this.pageUrl = pageUrl
                        this.width = w
                        this.height = h
                    })

                } catch (_:Exception){}
            }

        } catch (_:Exception){}
        return list
    }
}