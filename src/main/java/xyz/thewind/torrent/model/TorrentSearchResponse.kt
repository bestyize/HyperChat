package xyz.thewind.torrent.model

class TorrentSearchResponse {
    var code: Int = 0
    var list: MutableList<TorrentInfo> = mutableListOf()
}