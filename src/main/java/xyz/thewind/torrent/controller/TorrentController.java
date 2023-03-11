package xyz.thewind.torrent.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.thewind.torrent.datasource.DataSourceFactory;
import xyz.thewind.torrent.model.TorrentConfigResponse;
import xyz.thewind.torrent.model.TorrentInfo;
import xyz.thewind.torrent.model.TorrentSearchResponse;
import xyz.thewind.torrent.model.TorrentUrlResponse;

import java.util.List;

@RestController
public class TorrentController {

    @RequestMapping("/torrent/api/search")
    public ResponseEntity<TorrentSearchResponse> searchTorrent(int src, String keyword, int page) {
        List<TorrentInfo> res = DataSourceFactory.INSTANCE.newService(src).search(keyword, page);
        TorrentSearchResponse resp = new TorrentSearchResponse();
        resp.setCode(0);
        resp.setList(res);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(resp, headers,HttpStatus.OK);
    }

    @RequestMapping("/torrent/api/magnetlink")
    public ResponseEntity<TorrentUrlResponse> getMagnetLink(Integer src, String link) {
        TorrentUrlResponse resp = new TorrentUrlResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (link == null) {
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        }
        if (src == null) {
            src = 0;
        }
        String url = DataSourceFactory.INSTANCE.newService(src).findMagnetUrl(link);
        resp.setResult(url);
        return new ResponseEntity<>(resp, headers, HttpStatus.OK);
    }

    @RequestMapping("/torrent/api/supportsource")
    public ResponseEntity<TorrentConfigResponse> getSupportSource() {
        TorrentConfigResponse resp = new TorrentConfigResponse();
        resp.setResult(DataSourceFactory.INSTANCE.getSupportSources());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(resp, headers, HttpStatus.OK);
    }

}
