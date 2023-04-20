package xyz.thewind.community.image.service.impl.konachan.parser

import org.jsoup.Jsoup
import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.global.util.config.redis.konaCookie

object KonaChanParser {


    fun search(keyword: String, page: Int): MutableList<ImageInfo> {
        val requestUrl = "https://konachan.net/post?page=$page&tag=$keyword"
        val list = mutableListOf<ImageInfo>()
        val cookie = try {
            konaCookie ?: "cf_clearance=u7sxMP5Hj_lbbYEBEcsmvpAdKsgwe8y78IDh2e0f6Ps-1681928037-0-160;"
        } catch (_: Exception){
            "cf_clearance=u7sxMP5Hj_lbbYEBEcsmvpAdKsgwe8y78IDh2e0f6Ps-1681928037-0-160;"
        }
        try {
            val header = mutableMapOf(
                "user-agent" to "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.39",
                "cookie" to cookie
            )
            val content = HttpUtil.get(requestUrl, header)
            val data = Jsoup.parse(content).body().getElementById("post-list-posts")
            val items = data?.getElementsByTag("li")
            items?.forEach {
                try {
                    val id = it.attr("id").toString()
                    val previewImage = it.getElementsByTag("img")[0]
                    val previewImageUrl = previewImage.attr("src")
                    val alt = previewImage.attr("title")
                    val pageUrl = "https://konachan.net/post/show/" + id.replace("p", "")
                    val imageUrl = it.getElementsByClass("largeimg")[0].attr("href").toString()
                    val whStr = it.getElementsByClass("directlink-res")[0].text()
                    val w = whStr.replace(" ", "").split("x")[0].toInt()
                    val h = whStr.replace(" ", "").split("x")[1].toInt()
                    list.add(ImageInfo().apply {
                        this.src = ImageSrc.KONACHAN.src
                        this.id = id
                        this.imageUrl = imageUrl
                        this.previewImageUrl = previewImageUrl
                        this.width = w
                        this.height = h
                        this.tags = alt
                        this.pageUrl = pageUrl
                        this.downloadExtras.putAll(header)
                    })
                } catch (_: Exception){}
            }
        } catch (_:Exception){}
        return list

    }
}