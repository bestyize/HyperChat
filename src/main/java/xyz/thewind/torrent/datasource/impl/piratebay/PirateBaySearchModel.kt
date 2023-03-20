package xyz.thewind.torrent.datasource.impl.piratebay

import org.jsoup.Jsoup
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_PAGE
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_WORD
import xyz.thewind.torrent.datasource.def.TorrentSrc
import xyz.thewind.torrent.datasource.def.urlEncode
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchModel
import xyz.thewind.torrent.model.TorrentInfo

private const val BASE_URL = "https://thepiratebay.party"
private const val SEARCH_URL = "$BASE_URL/search/$KEY_REPLACE_WORD/$KEY_REPLACE_PAGE/99/0"

object PirateBaySearchModel : TorrentSearchModel {


    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        val realPage = SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        val elements = Jsoup.parse(HttpUtil.get(realPage)).body().getElementsByTag("tr")
        elements.forEach {
            try {
                val tds = it.getElementsByTag("td")
                val title = tds[1].text()
                val detailLink = tds[1].getElementsByAttribute("href")[0].attr("href")
                val date = tds[2].text()
                val magnetLink = tds[3].getElementsByAttribute("href")[0].attr("href")
                val size = tds[4].text()
                list.add(TorrentInfo().apply {
                    this.title = title
                    this.size = size
                    this.src = TorrentSrc.PIRATE_BAY.ordinal
                    this.date = date
                    this.detailUrl = detailLink
                    this.magnetUrl = magnetLink
                })

            } catch (_: Exception) {

            }
        }

        return list
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        try {
            return Jsoup.parse(HttpUtil.get(link)).body().getElementsByClass("download")[0].getElementsByTag("a")[0].attr("href")
        } catch (e: Exception) {

        }
        return ""
    }
}