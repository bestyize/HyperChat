package xyz.thewind.global.util.config.redis

import xyz.thewind.global.util.ApplicationUtils
import xyz.thewind.music.service.RedisService


fun getRedisService(): RedisService? {
    val service = ApplicationUtils.CONTEXT.autowireCapableBeanFactory.getBean(
        RedisService::class.java
    )
    return if (!service.isRedisActive) {
        null
    } else service
}
