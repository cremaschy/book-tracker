package br.com.fatec.book.tracker.data.remote.di

import android.util.Log
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

class KtorLogger() : Logger {
    override fun log(message: String) {
        if(message.startsWith("{") || message.startsWith("[")) {
            Log.d("KTOR", prettyJson(message))
        }
    }

    fun prettyJson(json: String) : String {
        return try {
            val parsedJson = Json.parseToJsonElement(json)
            Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), parsedJson)
        } catch (_: Exception) {
            json
        }
    }
}
