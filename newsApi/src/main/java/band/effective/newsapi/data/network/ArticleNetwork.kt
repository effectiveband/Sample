package band.effective.newsapi.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/*
    {
        -"source": {
            "id": "bbc-news",
            "name": "BBC News"
        },
        "author": "BBC News",
        "title": "Ghostly monkey and crocodile newts among new Mekong area species",
        "description": "A list of 224 newly discovered species is published by the World Wildlife Fund conservation group.",
        "url": "http://www.bbc.co.uk/news/world-asia-60106144",
        "urlToImage": "https://ichef.bbci.co.uk/news/1024/branded_news/A4B5/production/_122956124_771c5b7a-ef21-4766-ba76-9d5144846b2d.jpg",
        "publishedAt": "2022-01-26T10:37:23.858874Z",
        "content": "Image source, Press Association\r\nImage caption, The Popa langur monkey has ghostly white circles around its eyes\r\nThe World Wildlife Fund (WWF) conservation group has published a list of 224 newly di… [+1324 chars]"
    }
 */

@JsonClass(generateAdapter = true)
class ArticleNetwork(
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "source")
    val source: ArticleSourceNetwork,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val imageUrl: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "publishedAt")
    val date: Date
)

/*
    {
        "id": "cnn",
        "name": "CNN"
    }
 */

@JsonClass(generateAdapter = true)
class ArticleSourceNetwork(
    @Json(name = "name")
    val name: String
)