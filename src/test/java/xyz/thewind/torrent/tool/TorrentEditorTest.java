package xyz.thewind.torrent.tool;

import org.junit.jupiter.api.Test;
import xyz.thewind.global.util.json.JsonUtilsKt;
import xyz.thewind.torrent.datasource.DataSourceFactory;
import xyz.thewind.torrent.datasource.def.TorrentSrc;
import xyz.thewind.torrent.model.TorrentInfo;
import xyz.thewind.torrent.tool.editor.TorrentEditor;

import java.util.List;
import java.util.Map;

public class TorrentEditorTest {

    @Test
    public void testParseFile() {
        String path = "/home/read/Desktop/torr/1661216054.torrent";
        Map<String, Object> map = TorrentEditor.INSTANCE.decodeTorrentFileToMap(path, true);
        //TorrentEditor.INSTANCE.saveTorrentFile(TorrentEditor.INSTANCE.washTorrent(map), "/home/read/Desktop/torr/wash_1661216054.torrent");
        System.out.println(JsonUtilsKt.toJson(TorrentEditor.INSTANCE.parseTorrentFile(path)));
    }

    @Test
    public void testAcgRip() {
        List<TorrentInfo> infos = DataSourceFactory.INSTANCE.newService(TorrentSrc.ACG_RIP.ordinal()).search("心烦", 1);

        System.out.println(JsonUtilsKt.toJson(infos));
    }

    @Test
    public void testBay() {
        List<TorrentInfo> infos = DataSourceFactory.INSTANCE.newService(TorrentSrc.PIRATE_BAY.ordinal()).search("ida pro", 1);

        System.out.println(JsonUtilsKt.toJson(infos));

        String magnet = DataSourceFactory.INSTANCE.newService(TorrentSrc.PIRATE_BAY.ordinal()).findMagnetUrl("https://thepiratebay.party/torrent/12499831/MICROSOFT_Office_PRO_Plus_2016_v16.0.4266.1003_RTM___Activator");

        System.out.println(magnet);
    }
    @Test
    public void testYuHuaGe() {
        List<TorrentInfo> infos = DataSourceFactory.INSTANCE.newService(TorrentSrc.YU_HUA_GE.ordinal()).search("口", 1);

        System.out.println(JsonUtilsKt.toJson(infos));

        for (TorrentInfo info: infos) {
            String magnet = DataSourceFactory.INSTANCE.newService(TorrentSrc.YU_HUA_GE.ordinal()).findMagnetUrl(info.getDetailUrl());

            System.out.println(magnet);
        }

    }

    @Test
    public void testSolidTorrent() {
        List<TorrentInfo> infos = DataSourceFactory.INSTANCE.newService(TorrentSrc.SOLID_TORRENT.ordinal()).search("流浪地球", 1);

        System.out.println(JsonUtilsKt.toJson(infos));

        for (TorrentInfo info: infos) {
            String magnet = DataSourceFactory.INSTANCE.newService(TorrentSrc.SOLID_TORRENT.ordinal()).findMagnetUrl(info.getDetailUrl());

            System.out.println(magnet);
        }
    }
}
