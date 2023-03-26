package xyz.thewind.user.model.wx

/**
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>1348831860</CreateTime>
 *   <MsgType><![CDATA[text]]></MsgType>
 *   <Content><![CDATA[this is a test]]></Content>
 *   <MsgId>1234567890123456</MsgId>
 *   <MsgDataId>xxxx</MsgDataId>
 *   <Idx>xxxx</Idx>
 * </xml>
 */
class WxReceiveMessage {

    var toUserName: String = ""
    var fromUserName: String = ""
    var createTime: String = ""
    var msgType: String = ""
    var content: String = ""
    var msgId: String = ""
    var msgDataId: String = ""
    var ids: String = ""


    companion object {
        fun decode(msg: String?): WxReceiveMessage {
            val message = WxReceiveMessage()
            msg ?: return message
            try {
                message.toUserName = msg.substringAfter("<ToUserName><![CDATA[").substringBefore("]]></ToUserName>")
                message.fromUserName = msg.substringAfter("<FromUserName><![CDATA[").substringBefore("]]></FromUserName>")
                message.createTime = msg.substringAfter("<CreateTime>").substringBefore("</CreateTime>")
                message.msgType = msg.substringAfter("<MsgType><![CDATA[").substringBefore("]]></MsgType>")
                message.content = msg.substringAfter("<Content><![CDATA[").substringBefore("]]></Content>")
                message.msgId = msg.substringAfter("<MsgId>").substringBefore("</MsgId>")
                message.msgDataId = msg.substringAfter("<MsgDataId>").substringBefore("</MsgDataId>")
                message.ids = msg.substringAfter("<Idx>").substringBefore("</Idx>")
            } catch (_:Exception) {

            }

            return message
        }
    }

}