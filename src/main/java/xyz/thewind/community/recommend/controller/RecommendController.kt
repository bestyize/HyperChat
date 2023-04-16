package xyz.thewind.community.recommend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.community.recommend.bean.TopicListResponse
import xyz.thewind.community.recommend.service.RecommendTopicServiceHelper

@RestController
class RecommendController {

    @RequestMapping("/community/api/recommend/topics")
    fun getRecommendTopicList(): ResponseEntity<TopicListResponse> {
        return ResponseEntity(RecommendTopicServiceHelper.loadDefaultRecommendTopicList(), HttpStatus.OK)
    }

}