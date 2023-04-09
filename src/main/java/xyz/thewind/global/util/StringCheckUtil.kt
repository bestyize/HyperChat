package xyz.thewind.global.util

object StringCheckUtil {
    fun isValidPassword(password: String?): Boolean {
        password ?: return false
        // 检查密码长度是否大于 6
        if (password.length < 6) {
            return false
        }

        // 记录出现过的字符类型的数量
        var upperCount = 0
        var lowerCount = 0
        var digitCount = 0
        var underlineCount = 0
        var atCount = 0

        // 检查每个字符是否是大写字母、小写字母、数字、下划线或 @ 符号
        for (ch in password) {
            when {
                ch.isUpperCase() -> upperCount++
                ch.isLowerCase() -> lowerCount++
                ch.isDigit() -> digitCount++
                ch == '_' -> underlineCount++
                ch == '@' -> atCount++
                else -> return false  // 如果包含其他字符，返回 false
            }
        }

        // 检查至少包含两种以上的字符类型
        val typeCount = listOf(upperCount, lowerCount, digitCount, underlineCount, atCount).count { it > 0 }
        return typeCount >= 2
    }

    fun isValidUserName(username: String?): Boolean {
        username?:return false
        // 长度需大于1个字符
        if (username.length <= 1) {
            return false
        }

        // 由汉字、字母、数字、下划线中的一种或多种字符组成
        val regex = Regex("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$")
        if (!username.matches(regex)) {
            return false
        }

        // 不能为纯数字
        return !username.all { it.isDigit() }
    }


}