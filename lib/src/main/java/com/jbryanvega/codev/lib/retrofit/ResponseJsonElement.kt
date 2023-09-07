package com.jbryanvega.codev.lib.retrofit

import com.google.gson.Gson
import com.google.gson.JsonElement

class ResponseJsonElement constructor() : JsonElement() {

    companion object {
        val RETROFIT_API_FAILURE = "retrofit_api_failure"
        val NO_NETWORK = "no_network"
        val INVALID_ARGUMENT = "invalid_argument"
        val MISSING_ARGUMENT = "missing_argument"
        val MISSING_TOKEN = "missing_token"
        val MISSING_REFRESH_TOKEN = "missing_refresh_token"

        val SUCCESS = "success"

        val CODE_KEY = "code"
        val MESSAGE_KEY = "message"

        val API_FAILURE_JSON_ELEMENT = ResponseJsonElement(RETROFIT_API_FAILURE, "retrofit api failure")
        val INVALID_ARGUMENT_ERROR = ResponseJsonElement(INVALID_ARGUMENT, "invalid argument")
        val MISSING_ARGUMENT_ERROR = ResponseJsonElement(MISSING_ARGUMENT, "missing argument")
        val MISSING_TOKEN_ERROR = ResponseJsonElement(MISSING_TOKEN, "missing token")
        val MISSING_REFRESH_TOKEN_ERROR = ResponseJsonElement(MISSING_REFRESH_TOKEN, "missing refresh token")
        val NO_NETWORK_ERROR = ResponseJsonElement(NO_NETWORK, "no network")
    }

    private var element: JsonElement? = null

    constructor(jsonElement: JsonElement) : this() {
        element = jsonElement
    }

    constructor(code: String, message: String) : this() {
        var jsonStr = "{\"code\": \"code_value\", \"message\": \"message_value\"}"
        jsonStr = jsonStr.replace("code_value", code).replace("message_value", message)
        val gson = Gson()
        element = gson.fromJson(jsonStr, JsonElement::class.java)
    }

    fun getJsonElement(): JsonElement? {
        return element
    }

    fun getCode(): String {
        var code = ""
        try {
            val codeObject = element!!.asJsonObject
            code = codeObject[CODE_KEY].asString
        } catch (ignore: Exception) {
        }
        return code
    }

    fun getMessage(): String {
        var message = ""
        try {
            val messageObject = element!!.asJsonObject
            message = messageObject[MESSAGE_KEY].asString
        } catch (ignore: Exception) {
        }
        return message
    }

    override fun deepCopy(): JsonElement? {
        return null
    }
}