package xyz.thewind.torrent.datasource.def

import java.net.URLEncoder


fun String.urlEncode(): String {
    return URLEncoder.encode(this, "UTF-8")
}