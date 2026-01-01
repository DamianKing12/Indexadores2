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

    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    // Usamos implementation como en el repositorio funcional
    implementation("com.github.recloudstream:cloudstream:master-SNAPSHOT")
    implementation(kotlin("stdlib"))
    implementation("com.github.Blatzar:NiceHttp:0.4.11")
    implementation("org.jsoup:jsoup:1.18.3")
}

cloudstream {
    // Estas líneas son para metadatos, pero no usaremos 'make'
    description = "Plugin Indexador para SeriesKao"
    authors = listOf("DamianKing12")
}
