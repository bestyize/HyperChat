package xyz.thewind.user.bean

import xyz.thewind.user.database.bean.User

class UpdateUserInfoResponse {
    var code: Int = -1
    var msg: String? = null
    var data: User? = null
}