package xyz.thewind.torrent.datasource.impl.base

import xyz.thewind.torrent.model.TorrentInfo

interface TorrentSearchService {

    fun search(key: String, page: Int = 1) : List<TorrentInfo>

    fun findMagnetUrl(url: String) : String
}