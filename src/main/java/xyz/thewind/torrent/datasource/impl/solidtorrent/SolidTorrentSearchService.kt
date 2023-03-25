package xyz.thewind.torrent.datasource.impl.solidtorrent

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo

class SolidTorrentSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return SolidTorrentSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return SolidTorrentSearchModel.getMagnet(url)
    }
}