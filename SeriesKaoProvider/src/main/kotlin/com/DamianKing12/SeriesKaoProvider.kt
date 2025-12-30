package com.DamianKing12

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.BasePlugin

@CloudstreamPlugin
class SeriesKaoPlugin : BasePlugin() {
    override fun load() {
        // Registro del proveedor de búsqueda
        registerMainAPI(SeriesKaoProvider())
    }
}

class SeriesKaoProvider : MainAPI() {
    override var name = "SeriesKao"
    override var mainUrl = "https://serieskao.top"
    override var supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override suspend fun search(query: String): List<SearchResponse> {
        val url = "$mainUrl/?s=$query"
        val document = app.get(url).document
        return document.select("div.result-item").mapNotNull {
            val title = it.selectFirst("div.title a")?.text() ?: return@mapNotNull null
            val href = it.selectFirst("div.title a")?.attr("href") ?: ""
            val poster = it.selectFirst("img")?.attr("src")

            newMovieSearchResponse(title, href, TvType.Movie) {
                this.posterUrl = poster
            }
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val doc = app.get(data).document
        doc.select("iframe").amap {
            var iframeUrl = it.attr("src")
            if (iframeUrl.startsWith("//")) {
                iframeUrl = "https:$iframeUrl"
            }
            loadExtractor(iframeUrl, data, subtitleCallback, callback)
        }
        return true
    }
}
