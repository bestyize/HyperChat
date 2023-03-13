package xyz.thewind.global.util.json

import com.fasterxml.jackson.databind.ObjectMapper

private val mapper = ObjectMapper()

fun Any?.toJson(): String {
    this ?: return ""
    return mapper.writeValueAsString(this)
}

fun Any?.printToJson() {
    println(toJson())
}