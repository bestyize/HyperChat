package com.speaker.torrent.datasource.impl.btshow

import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.model.TorrentInfo
import xyz.thewind.torrent.datasource.impl.btshow.BtShowSearchModel

class BtShowSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return BtShowSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return BtShowSearchModel.getMagnet(url)
    }
}