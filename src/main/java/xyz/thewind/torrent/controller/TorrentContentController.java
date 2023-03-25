package xyz.thewind.torrent.controller;

import com.xunlei.download.config.TorrentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TorrentContentController {

    @RequestMapping(value = "/torrent/api/magnet/torrent", method = RequestMethod.POST)
    public RedirectView magnetToTorrent(String magnet) {
        String hash = TorrentUtil.INSTANCE.getMagnetHash(magnet);
        return new RedirectView("https://itorrents.org/torrent/"+hash+".torrent");
    }

}
