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

// Usamos la URL estándar del repositorio. El plugin detectará que es GitHub.
configure<com.lagradost.cloudstream3.gradle.CloudstreamExtension> {
    setRepo("https://github.com/DamianKing12/Indexadores2")
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
