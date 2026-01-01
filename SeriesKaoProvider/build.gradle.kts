plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

android {
    namespace = "com.DamianKing12"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

cloudstream {
    // ESTO ES LO MÁS IMPORTANTE: Nombre interno fijo para que coincida con el JSON
    internalName = "SeriesKao"
    
    version = 1
    description = "Plugin Indexador para SeriesKao"
    authors = listOf("DamianKing12")
    status = 1
    tvTypes = listOf("TvSeries", "Movie")
    requiresResources = false
    language = "es"
    iconUrl = "https://www.google.com/s2/favicons?domain=serieskao.top&sz=%size%"
    
    setRepo("DamianKing12", "Indexadores2", "github")
}

dependencies {
    compileOnly("com.github.recloudstream:cloudstream:master-SNAPSHOT")
    compileOnly("com.github.Blatzar:NiceHttp:0.4.11")
    compileOnly("org.jsoup:jsoup:1.18.3")
    implementation(kotlin("stdlib"))
}
