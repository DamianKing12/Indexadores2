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

dependencies {
    // Usamos compileOnly para evitar duplicar librerías que la App de Cloudstream ya tiene.
    // Esto es vital para evitar conflictos en tiempo de ejecución.
    compileOnly("com.github.recloudstream:cloudstream:master-SNAPSHOT")
    compileOnly("com.github.Blatzar:NiceHttp:0.4.11")
    compileOnly("org.jsoup:jsoup:1.18.3")
    
    implementation(kotlin("stdlib"))
}

cloudstream {
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
