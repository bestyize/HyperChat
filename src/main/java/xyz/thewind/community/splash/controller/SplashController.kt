package xyz.thewind.community.splash.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.community.splash.model.*
import xyz.thewind.global.util.NumberUtil

@RestController
class SplashController {

    @RequestMapping("/community/api/splash/startup")
    fun startCheck(httpServletRequest: HttpServletRequest): ResponseEntity<StartCheckResponse> {
        val versionStr = httpServletRequest.getHeader("versionCode")
        val versionCode = NumberUtil.getOrDefault(versionStr, 0)
        val resp = StartCheckResponse()
        if (versionCode < 100) {
            val rightButton = ButtonData().apply {
                this.text = "去升级"
                this.actionType = ButtonActionType.OUTER_JUMP
                this.actionUrl = "https://thewind.xyz"
            }
            resp.data = NotifyData().apply {
                this.type = NotifyType.APP_UPDATE
                this.content = "发现新版本，请升级"
                this.cancelable = false
                this.rightButton = rightButton
            }
        }
        return ResponseEntity(resp, HttpStatus.OK)
    }

}