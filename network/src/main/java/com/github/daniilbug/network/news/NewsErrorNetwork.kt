package com.github.daniilbug.network.news

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
    {
        "status": "error",
        "code": "apiKeyMissing",
        "message": "Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header."
    }
 */

@JsonClass(generateAdapter = true)
class NewsErrorNetwork(
    @Json(name = "status")
    val status: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "message")
    val message: String
)