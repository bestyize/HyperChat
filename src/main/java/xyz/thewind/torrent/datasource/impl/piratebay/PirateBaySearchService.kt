package xyz.thewind.torrent.datasource.impl.piratebay

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo

class PirateBaySearchService: TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return PirateBaySearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return PirateBaySearchModel.getMagnet(url)
    }
}