package xyz.thewind.user.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.global.util.StringCheckUtil
import xyz.thewind.user.database.service.UserService
import xyz.thewind.user.model.wx.WxReceiveMessage
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.concurrent.TimeUnit


@RestController
class UserController {

    @Autowired
    private val redisTemplate: StringRedisTemplate? = null

    @Autowired
    private val userService: UserService? = null

    @GetMapping("/api/wechat/wx")
    fun wxTokenVerify(
        signature: String? = "",
        timestamp: String? = "",
        nonce: String? = "",
        echostr: String? = ""
    ): String {
        return if (checkSignature(signature, "bestyize", timestamp, nonce)) echostr ?: "" else "error"
    }

    @PostMapping("/api/wechat/wx")
    fun wxReplayUserMsg(@RequestBody requestBody: String?): String {
        return handleUserMessage(requestBody)
    }

    private fun handleUserMessage(requestBody: String?): String {
        val message = WxReceiveMessage.decode(requestBody)
        if (message.msgType != "text") return assembleReplayData(
            "不支持图片、视频消息",
            toUserName = message.fromUserName,
            fromUserName = message.toUserName
        )
        return assembleReplayData(
            hitMusic(message.content, message.fromUserName),
            toUserName = message.fromUserName,
            fromUserName = message.toUserName
        )
    }


    private fun hitMusic(text: String, openId: String): String {
        return when {
            text.contains("音乐") || text.contains("教程") || text.contains("无名") -> "教程:https://mp.weixin.qq.com/s/-W13vOAK7hicZ6aYq3ifYQ"
            text.contains("限制") -> "因站点访问量过大，需要限制流量，触发IP限制的时长为6小时，您可以在六小时后重试，或者切换网络改变IP"
            text.contains("验证码") -> {
                var code = rand(100000, 999999).toString()
                var retryTime = 5
                while (redisTemplate?.opsForValue()?.get(code) != null && retryTime > 0) {
                    code = rand(100000, 999999).toString()
                    retryTime--
                }
                redisTemplate?.opsForValue()?.set(code, openId, 10, TimeUnit.MINUTES)
                "您的验证码是:$code, 10分钟内有效"
            }
            text.contains("找回账号") -> {
                val user = userService?.findUserByOpenId(openId)
                if (user != null) {
                    "您的用户名是:${user.userName},您的密码是:${user.token}"
                } else {
                    "您还不是注册用户, 请前往APP注册"
                }
            }
            text.contains("重置密码") -> {
                val user = userService?.findUserByOpenId(openId)
                when{
                    user == null -> "您还不是注册用户, 请前往APP注册"
                    text.length < 10 -> "密码长度太短"
                    text.length >= 10 -> {
                        val token = text.substring(IntRange("重置密码".length, text.length - 1))
                        if (StringCheckUtil.isValidPassword(token)) {
                            userService?.updateTokenByOpenId(openId, token)
                            "重置成功！"
                        } else {
                            "密码必须由数字、大写字母、小写字母组成，至少包含其中的两种"
                        }

                    }
                    else -> "您还不是注册用户, 请前往APP注册"
                }
            }
            else -> "QQ群:972084185"
        }
    }

    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }


    private fun checkSignature(msgSignature: String?, token: String?, timeStamp: String?, nonce: String?): Boolean {
        if (msgSignature == null || token == null || timeStamp == null || nonce == null) return false
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        val arr = arrayOf(token, timeStamp, nonce)
        Arrays.sort(arr)

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        val content = StringBuilder()
        for (i in arr.indices) {
            content.append(arr[i])
        }
        var md: MessageDigest? = null
        var tmpStr: String? = null
        try {
            md = MessageDigest.getInstance("SHA-1")
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            val digest: ByteArray = md.digest(content.toString().toByteArray())
            tmpStr = byteToHex(digest)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return if (tmpStr != null) tmpStr == msgSignature.lowercase() else false
    }


    private fun byteToHex(hash: ByteArray): String {
        val formatter = Formatter()
        for (b in hash) {
            formatter.format("%02x", b)
        }
        val result = formatter.toString()
        formatter.close()
        return result
    }

    private fun assembleReplayData(content: String, toUserName: String, fromUserName: String): String {
        return "<xml><ToUserName><![CDATA[${toUserName}]]></ToUserName><FromUserName><![CDATA[$fromUserName]]></FromUserName><CreateTime>${System.currentTimeMillis() / 1000}</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[$content]]></Content></xml>"
    }
}