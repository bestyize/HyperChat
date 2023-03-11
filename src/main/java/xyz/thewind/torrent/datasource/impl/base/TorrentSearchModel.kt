package xyz.thewind.torrent.datasource.impl.base

import xyz.thewind.torrent.model.TorrentInfo

interface TorrentSearchModel {
    fun searchParse(key: String, page: Int) : List<TorrentInfo>

    fun getMagnet(link: String?): String

    fun getMirrorSite(originUrl: String): String {return ""}
}