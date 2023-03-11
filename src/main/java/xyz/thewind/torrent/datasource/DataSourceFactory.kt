package xyz.thewind.torrent.datasource

import com.speaker.torrent.datasource.impl.btshow.BtSowSearchService
import xyz.thewind.torrent.datasource.def.TorrentSrc
import xyz.thewind.torrent.datasource.impl.base.TorrentSearchService
import xyz.thewind.torrent.datasource.impl.torlock.TorLockSearchService
import xyz.thewind.torrent.datasource.impl.x1337.X1337SearchService
import xyz.thewind.torrent.datasource.impl.yuhuage.YuHuaGeSearchService
import xyz.thewind.torrent.datasource.impl.zeromagnet.ZeroMagnetSearchService
import xyz.thewind.torrent.model.TorrentSource

object DataSourceFactory {

    private val servicesPool by lazy{ mutableMapOf<Int, TorrentSearchService>().apply {
        put(TorrentSrc.ZERO_MAGNET.ordinal, ZeroMagnetSearchService())
        put(TorrentSrc.X_1337.ordinal, X1337SearchService())
        put(TorrentSrc.TOR_LOCK.ordinal, TorLockSearchService())
        put(TorrentSrc.BT_SOW.ordinal, BtSowSearchService())
        put(TorrentSrc.YU_HUA_GE.ordinal, YuHuaGeSearchService())
    }}

    fun newService(torrentSrc: Int = TorrentSrc.ZERO_MAGNET.ordinal) : TorrentSearchService {
        return servicesPool[torrentSrc] ?: ZeroMagnetSearchService()
    }

    fun getSupportSources(): List<TorrentSource> {
        return supportList

    }

    private val supportList by lazy {
        mutableListOf<TorrentSource>().apply {
            add(TorrentSource().apply {
                src = TorrentSrc.ZERO_MAGNET.ordinal
                title = "ZeroMagnet"
                officialUrl = "https://0magnet.com"
            })
            add(TorrentSource().apply {
                src = TorrentSrc.X_1337.ordinal
                title = "1337"
                officialUrl = "https://www.1337xx.to"
            })
            add(TorrentSource().apply {
                src = TorrentSrc.TOR_LOCK.ordinal
                title = "TorLock"
                officialUrl = "https://www.torlock.com"
            })
            add(TorrentSource().apply {
                src = TorrentSrc.TOR_LOCK.ordinal
                title = "BtSow"
                officialUrl = "https://btsow.com"
            })
            add(TorrentSource().apply {
                src = TorrentSrc.TOR_LOCK.ordinal
                title = "雨花阁"
                officialUrl = "https://www.yuhuage.win/"
            })
        }
    }

}