plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

android {
    namespace = "com.DamianKing12.serieskao"
    compileSdk = 35
    
    defaultConfig {
        minSdk = 21
        // targetSdk en librerías está obsoleto, con compileSdk es suficiente
    }
    
    buildFeatures {
        buildConfig = true
        viewBinding = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Eliminamos kotlinOptions obsoleto para evitar el error del compilador
}

cloudstream {
    // El nombre se toma automáticamente de la clase Provider, no usamos name ni internalName aquí
    description = "Plugin Indexador para SeriesKao"
    authors = listOf("DamianKing12")
    status = 1
    tvTypes = listOf("TvSeries", "Movie")
    requiresResources = false
    language = "es"
    iconUrl = "https://www.google.com/s2/favicons?domain=serieskao.top&sz=%size%"
    
    // Usamos la URL estándar que el plugin reconoce
    setRepo("https://github.com/DamianKing12/Indexadores2")
}

dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    compileOnly("com.github.recloudstream:cloudstream:master-SNAPSHOT")
    
    implementation(kotlin("stdlib"))
    implementation("com.github.Blatzar:NiceHttp:0.4.11")
    implementation("org.jsoup:jsoup:1.18.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
}
