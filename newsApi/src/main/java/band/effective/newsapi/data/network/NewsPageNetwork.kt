package band.effective.newsapi.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class NewsPageNetwork(
    @Json(name = "totalResults")
    val total: Int,
    @Json(name = "articles")
    val articles: List<ArticleNetwork>
)