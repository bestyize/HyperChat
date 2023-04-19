package xyz.thewind.global.util.config.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.music.service.RedisService

@RestController
class RedisConfigController {

    @Autowired
    private var redisService: RedisService? = null

    @RequestMapping("/community/api/config/str")
    fun setConfigString(key: String?, value: String?): ResponseEntity<String> {
        if (key == null || value == null || key.isEmpty()) return ResponseEntity("更新失败", HttpStatus.OK)
        try {
            redisService?.set(key, value)
        } catch (e: Exception) {
            return ResponseEntity("更新失败, e = ${e.cause}", HttpStatus.OK)
        }
        return ResponseEntity("更新成功", HttpStatus.OK)
    }

    @RequestMapping("/community/api/config/str")
    fun getConfigString(key: String?): ResponseEntity<String> {
        if (key == null || key.isEmpty()) return ResponseEntity("获取失败", HttpStatus.OK)
        return try {
            ResponseEntity(redisService?.get(key)?:"", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity("更新失败, e = ${e.cause}", HttpStatus.OK)
        }
    }

}