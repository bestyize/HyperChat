package xyz.thewind.torrent.datasource.impl.torlock

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo

class TorLockSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return TorLockSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return TorLockSearchModel.getMagnet(url)
    }
}