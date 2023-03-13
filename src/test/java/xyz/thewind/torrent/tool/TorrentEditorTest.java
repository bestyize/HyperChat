package xyz.thewind.torrent.tool;

import org.junit.jupiter.api.Test;
import xyz.thewind.torrent.tool.editor.TorrentEditor;

import java.util.Map;

public class TorrentEditorTest {

    @Test
    public void testParseFile() {
        String path = "/home/read/Desktop/torr/1661216054.torrent";
        Map<String, Object> map = TorrentEditor.INSTANCE.decodeTorrentFileToMap(path);
        TorrentEditor.INSTANCE.saveTorrentFile(TorrentEditor.INSTANCE.washTorrent(map), "/home/read/Desktop/torr/wash_1661216054.torrent");
        System.out.println(map);
    }

}
