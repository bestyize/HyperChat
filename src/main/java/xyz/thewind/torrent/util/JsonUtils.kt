package xyz.thewind.torrent.util

import com.fasterxml.jackson.databind.ObjectMapper

object JsonUtils {

    private val mapper = ObjectMapper()

    fun toJson(obj: Any?): String {
        obj ?: return ""
        return mapper.writeValueAsString(obj)
    }

    fun printToJson(obj: Any?) {
        println(toJson(obj))
    }
}