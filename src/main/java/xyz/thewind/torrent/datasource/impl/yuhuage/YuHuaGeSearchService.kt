package xyz.thewind.torrent.datasource.impl.yuhuage

import xyz.thewind.torrent.model.TorrentInfo
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService


class YuHuaGeSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return YuHuaGeSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return YuHuaGeSearchModel.getMagnet(url)
    }
}