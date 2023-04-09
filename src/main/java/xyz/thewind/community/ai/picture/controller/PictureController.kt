package xyz.thewind.community.ai.picture.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.community.ai.picture.datasource.impl.adobe.AdobePictureService
import xyz.thewind.community.ai.picture.model.PictureResponse

@RestController
class PictureController {

    @RequestMapping("/community/ai/api/picture/inspires")
    fun  getInspireList(src: Int?):ResponseEntity<PictureResponse> {
        return ResponseEntity(PictureResponse().apply {
            this.code = 0
            this.data = AdobePictureService.requestImageInspirations()
        }, HttpStatus.OK)
    }

    @RequestMapping("/community/ai/api/picture/styles")
    fun  getStyleList(src: Int?):ResponseEntity<PictureResponse> {
        return ResponseEntity(PictureResponse().apply {
            this.code = 0
            this.data = AdobePictureService.requestImageStyles()
        }, HttpStatus.OK)
    }

}