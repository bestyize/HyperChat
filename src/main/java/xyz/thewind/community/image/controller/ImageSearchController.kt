package xyz.thewind.community.image.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.community.image.model.ImageRecommendTabResponse
import xyz.thewind.community.image.model.ImageSearchResponse
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.community.image.service.ImageDispatcher

@RestController
class ImageSearchController {

    @RequestMapping("/community/api/picture/search")
    fun search(keyword: String?, page: Int?, num: Int?, src: Int?) :ResponseEntity<ImageSearchResponse>{
        val resp = ImageSearchResponse()
        val list = ImageDispatcher.search(keyword, page, num, src)
        resp.code = 0
        resp.msg = "搜索到${list.size}条结果"
        resp.data = list
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @RequestMapping("/community/api/picture/recommend/tabs")
    fun recommendTabs(src: Int? = null): ResponseEntity<ImageRecommendTabResponse> {
        val resp = ImageRecommendTabResponse()
        resp.code = 0
        resp.data = ImageDispatcher.recommendTabs(src ?: ImageSrc.PEXELS.src)
        return ResponseEntity(resp, HttpStatus.OK)
    }
}