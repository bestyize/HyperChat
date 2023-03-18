package xyz.thewind.torrent.datasource.impl.acgrip

import org.jsoup.Jsoup
import xyz.thewind.global.util.HttpUtil.get
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_PAGE
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_WORD
import xyz.thewind.torrent.datasource.def.TorrentSrc
import xyz.thewind.torrent.datasource.def.urlEncode
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchModel
import xyz.thewind.torrent.model.TorrentInfo

private const val BASE_URL = "https://acg.rip"

private const val BASE_SEARCH_URL = "https://acg.rip/$KEY_REPLACE_PAGE?term=$KEY_REPLACE_WORD"

object AcgRipSearchModel: TorrentSearchModel {
    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val res: MutableList<TorrentInfo> = mutableListOf()

        try {
            val originUrl = BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
            val content = get(originUrl)
            val validEle = Jsoup.parse(content).body().getElementsByTag("tr")

            validEle.forEach { ele ->
                try {
                    val dateTime = ele.getElementsByAttribute("datetime")[0].text()
                    val title = ele.getElementsByClass("title").text()
                    val size = ele.getElementsByClass("size").text()
                    val torrentFileUrl = BASE_URL + ele.getElementsByClass("action")[0].getElementsByTag("a")[0].attr("href")
                    val detailUrl = torrentFileUrl
                    res.add(TorrentInfo().apply {
                        this.detailUrl = detailUrl
                        this.src = TorrentSrc.ACG_RIP.ordinal
                        this.date = dateTime
                        this.size = size
                        this.magnetUrl = torrentFileUrl
                        this.title = title
                    })
                }catch (e: Exception) {

                }

            }

        } catch (_ :Exception) {}

        return res
    }

    override fun getMagnet(link: String?): String {
        link?:return ""
        return "$link.torrent"
    }
}