package xyz.thewind.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    @RequestMapping("/")
    public String index() {
        return "music";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/player")
    public String play() {
        return "player";
    }

    @RequestMapping("/manager")
    public String manage() {
        return "manager";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "up";
    }

    @RequestMapping("/user_aggrement")
    public String userAggrement() {
        return "user_aggrement";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/ad")
    public String ad() {
        return "ad";
    }

    @RequestMapping("/app")
    public String app() {
        return "app";
    }
}
