package xyz.thewind.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.user.model.wx.WxReceiveMessage
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


@RestController
class UserController {

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
            hitMusic(message.content),
            toUserName = message.fromUserName,
            fromUserName = message.toUserName
        )
    }


    private fun hitMusic(text: String): String {
        return when {
            text.contains("音乐") || text.contains("教程") || text.contains("无名") -> "教程:https://mp.weixin.qq.com/s/-W13vOAK7hicZ6aYq3ifYQ"
            text.contains("验证码") -> "12345"
            text.contains("重置密码") -> ""
            else -> "QQ群:972084185"
        }
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