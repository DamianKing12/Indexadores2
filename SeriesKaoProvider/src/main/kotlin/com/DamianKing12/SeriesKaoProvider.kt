package com.DamianKing12

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.BasePlugin

@CloudstreamPlugin
class SeriesKaoPlugin : BasePlugin() {
    override fun load() {
        // Registro fundamental para que la App detecte el proveedor
        registerMainAPI(SeriesKaoProvider())
    }
}

class SeriesKaoProvider : MainAPI() {
    override var name = "SeriesKao"
    override var mainUrl = "https://serieskao.top"
    override var supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override var lang = "es"

    override suspend fun search(query: String): List<SearchResponse> {
        val url = "$mainUrl/?s=$query"
        val response = app.get(url)
        val document = response.document
        
        // Selector robusto para los items de búsqueda
        return document.select("article.item, div.result-item").mapNotNull {
            // Buscamos el link y el título en diferentes estructuras posibles
            val anchor = it.selectFirst("div.title a, h3 a, div.details a") ?: return@mapNotNull null
            val title = anchor.text()
            val href = anchor.attr("href")
            
            // Buscamos la imagen (poster) con soporte para lazy loading
            val poster = it.selectFirst("img")?.attr("data-src") 
                ?: it.selectFirst("img")?.attr("src")

            // Diferenciamos si es serie o película por la URL o etiquetas del sitio
            val isSerie = href.contains("/series/") || it.select(".tvshows").isNotEmpty()
            val type = if (isSerie) TvType.TvSeries else TvType.Movie

            if (isSerie) {
                newTvSeriesSearchResponse(title, href, type) {
                    this.posterUrl = poster
                }
            } else {
                newMovieSearchResponse(title, href, type) {
                    this.posterUrl = poster
                }
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
        
        // Buscamos tanto en iframes como en elementos con data-post (común en DooPlay)
        doc.select("iframe, .dooplay_player_option, li.dooplay_player_option").amap {
            var iframeUrl = it.attr("src")
            if (iframeUrl.isEmpty()) {
                // Algunos temas de WP ocultan el link o ID en data-post
                iframeUrl = it.attr("data-post") 
            }
            
            if (iframeUrl.isNotEmpty()) {
                if (iframeUrl.startsWith("//")) {
                    iframeUrl = "https:$iframeUrl"
                }
                // El sistema de Cloudstream intentará extraer el video del link
                loadExtractor(iframeUrl, data, subtitleCallback, callback)
            }
        }
        return true
    }
}
