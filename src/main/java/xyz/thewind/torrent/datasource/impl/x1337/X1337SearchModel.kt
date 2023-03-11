package xyz.thewind.torrent.datasource.impl.x1337

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchModel
import xyz.thewind.torrent.model.TorrentInfo
import org.jsoup.Jsoup
import xyz.thewind.global.util.HttpUtil.get
import xyz.thewind.global.util.log.Log
import xyz.thewind.torrent.datasource.def.*

private const val TAG = "X1337SearchModel"

private const val BASE_SEARCH_URL = "https://www.1337xx.to/search/$KEY_REPLACE_WORD/$KEY_REPLACE_PAGE/"
private const val BASE_DETAIL_URL = "https://www.1337xx.to"

object X1337SearchModel : TorrentSearchModel {

    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val reqUrl =
            BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        val resp = get(getMirrorSite(reqUrl))
        return parseSearch(resp)
    }

    private fun parseSearch(resp: String): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        val body = Jsoup.parse(resp).body()
        try {
            val items = body.getElementsByTag("tbody")[0].getElementsByTag("tr")
            items.forEach {
                val item1 = it.getElementsByClass("coll-1 name")
                val fileName = item1.text()
                val link = BASE_DETAIL_URL + item1[0].getElementsByTag("a")[1].attr("href")
                val size = it.getElementsByClass("coll-4 size mob-uploader").text()
                list.add(TorrentInfo().apply {
                    this.title = fileName
                    this.detailUrl = link
                    this.size = size
                    this.src = TorrentSrc.X_1337.ordinal
                })

            }
            return list
        } catch (e: Exception) {
            Log.i(TAG, e.toString())
        }
        return listOf()

    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        val body = Jsoup.parse(get(getMirrorSite(link)))
        try {
            return body.getElementsByClass(MAGNET_CLASS_NAME)[0].attr(
                "href"
            )
        } catch (_: Exception) {
            Log.i(TAG, "get magnet failed, url = $link")
        }
        return ""

    }

    override fun getMirrorSite(originUrl: String): String {
        //return originUrl
        return "${MIRROR_SITE}source=4&url=${originUrl.urlEncode()}"
    }


    private const val MAGNET_CLASS_NAME = "torrentdown2"

}