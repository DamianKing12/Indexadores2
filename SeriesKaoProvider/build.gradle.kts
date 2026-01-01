plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

android {
    namespace = "com.DamianKing12"
    compileSdk = 34 // Bajamos a 34 para compatibilidad con OpenGL

    defaultConfig {
        minSdk = 21
    }

    buildFeatures {
        buildConfig = true
        viewBinding = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Bajamos a Java 11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
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

dependencies {
    implementation("com.google.android.material:material:1.11.0")
    
    compileOnly("com.github.recloudstream:cloudstream:master-SNAPSHOT")
    
    implementation(kotlin("stdlib"))
    implementation("com.github.Blatzar:NiceHttp:0.4.11")
    implementation("org.jsoup:jsoup:1.18.3")
    // Eliminamos jackson-module-kotlin si no es estrictamente necesario para simplificar
}
