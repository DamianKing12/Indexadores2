buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    configurations.all {
        resolutionStrategy {
            force("com.github.vidstige:jadb:1.2.1")
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.7.3")
        classpath("com.github.recloudstream:gradle:cce1b8d84d")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
    }
}

apply(plugin = "com.lagradost.cloudstream3.gradle")

// Usamos esta sintaxis para evitar el error de "Unresolved reference" en la raíz
configure<com.lagradost.cloudstream3.gradle.CloudstreamExtension> {
    setRepo("https://raw.githubusercontent.com/DamianKing12/Indexadores2/builds/plugins.json")
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
