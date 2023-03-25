package xyz.thewind.torrent.datasource.impl.solidtorrent

import org.jsoup.Jsoup
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_PAGE
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_WORD
import xyz.thewind.torrent.datasource.def.urlEncode
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchModel
import xyz.thewind.torrent.model.TorrentInfo

private const val BASE_SEARCH_URL = "https://solidtorrent.to/search?q=$KEY_REPLACE_WORD&page=$KEY_REPLACE_PAGE"

private const val BASE_HOST = "https://solidtorrent.to"

object SolidTorrentSearchModel : TorrentSearchModel {
    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()

        val content = HttpUtil.get(
            BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        )
        val resList = Jsoup.parse(content).body().getElementsByClass("search-result")
        resList.forEach { ele ->
            try {
                val detailUrl = BASE_HOST + ele.getElementsByTag("h5")[0].getElementsByAttribute("href")[0].attr("href")
                val title = ele.getElementsByTag("h5")[0].getElementsByAttribute("href")[0].text()
                val stats = ele.getElementsByClass("stats")[0]
                val downloadCount = stats.getElementsByTag("div")[1].text()
                val fileSize = stats.getElementsByTag("div")[2].text()
                val senderCount = stats.getElementsByTag("div")[3].text()
                val leacher = stats.getElementsByTag("div")[4].text()
                val time = stats.getElementsByTag("div")[5].text()

                val eleDown = ele.getElementsByClass("links")[0]
                val torrentLink = eleDown.getElementsByAttribute("href")[0].attr("href")
                val magnetLink = eleDown.getElementsByAttribute("href")[1].attr("href")
                list.add(TorrentInfo().apply {
                    this.title = title
                    this.magnetUrl = magnetLink
                    this.torrentUrl = torrentLink
                    this.date = time
                    this.size = fileSize
                    this.detailUrl = detailUrl
                    this.downloadCount = downloadCount
                    this.leacherCount = leacher
                    this.senderCount = senderCount
                })

            } catch (_: Exception) {

            }

        }


        return list
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        try {
            return Jsoup.parse(HttpUtil.get(link)).body()
                .getElementsByClass("dl-links")[0].getElementsByAttribute("href")[1].attr("href")
        } catch (_: Exception) {

        }
        return ""
    }
}