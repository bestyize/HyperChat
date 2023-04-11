package xyz.thewind.community.splash.model


/**
 * @author: read
 * @date: 2023/4/11 下午11:56
 * @description:
 */
class StartCheckResponse {
    var code = 0
    var msg: String? = null
    var data: NotifyData? = null

}

class NotifyData {
    var type: Int = NotifyType.COMMON
    var cancelable: Boolean = false
    var title: String? = null
    var content: String? = null
    var leftButton: ButtonData? = null
    var rightButton: ButtonData? = null
}

class ButtonData {
    var text: String? = null
    var actionUrl: String? = null
    var actionType: Int = ButtonActionType.NONE
}

object NotifyType {
    const val COMMON = 0
    const val APP_UPDATE = 1
}

object ButtonActionType {
    const val NONE = 0
    const val INNER_H5 = 1
    const val INNER_PAGE = 2
    const val OUTER_JUMP = 3
}