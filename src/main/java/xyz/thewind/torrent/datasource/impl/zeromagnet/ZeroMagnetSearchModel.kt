package xyz.thewind.torrent.datasource.impl.zeromagnet

import xyz.thewind.torrent.datasource.def.KEY_DETAIL_REPLACE_DETAIL
import xyz.thewind.torrent.datasource.def.KEY_REPLACE_WORD
import xyz.thewind.torrent.datasource.def.urlEncode
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchModel
import xyz.thewind.torrent.model.TorrentInfo
import org.jsoup.Jsoup
import xyz.thewind.global.util.HttpUtil.get
import xyz.thewind.torrent.datasource.def.TorrentSrc

private const val BASE_SEARCH_URL = "https://0magnet.com/search?q=$KEY_REPLACE_WORD"
private const val BASE_DETAIL_URL = "https://0magnet.com$KEY_DETAIL_REPLACE_DETAIL"


object ZeroMagnetSearchModel : TorrentSearchModel {


    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val reqUrl = BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode())
        val resp = get(reqUrl)
        return parseSearch(resp)
    }

    private fun parseSearch(resp: String): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        val body = Jsoup.parse(resp).body()
        val elements = body.getElementsByTag("td")
        var index = 0
        while (index < elements.size - 1) {
            val ele1 = elements[index]
            val ele2 = elements[index + 1]
            val id = ele1.select("a").attr("href")
            val url = BASE_DETAIL_URL.replace(KEY_DETAIL_REPLACE_DETAIL, id)
            ele1.select("p").remove()
            val title = ele1.text()
            val size = ele2.text()
            index += 2
            list.add(TorrentInfo().apply {
                this.detailUrl = url
                this.title = title
                this.size = size
                this.src = TorrentSrc.ZERO_MAGNET.ordinal
            })
        }
        return list
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        val paramsMap = mutableMapOf("Referrer Policy" to "strict-origin-when-cross-origin")
        val body = Jsoup.parse(get(link, paramsMap))
        return body.getElementById("input-magnet")?.attr("value") ?: ""
    }


}