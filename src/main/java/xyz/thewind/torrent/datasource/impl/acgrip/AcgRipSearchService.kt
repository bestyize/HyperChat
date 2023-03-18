package xyz.thewind.torrent.datasource.impl.acgrip

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo

class AcgRipSearchService: TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return AcgRipSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return AcgRipSearchModel.getMagnet(url)
    }
}