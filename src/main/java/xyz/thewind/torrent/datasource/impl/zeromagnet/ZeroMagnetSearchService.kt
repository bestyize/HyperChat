package xyz.thewind.torrent.datasource.impl.zeromagnet

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo

private const val TAG = "ZeroMagnetSearchService"

class ZeroMagnetSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return ZeroMagnetSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return ZeroMagnetSearchModel.getMagnet(url)
    }
}