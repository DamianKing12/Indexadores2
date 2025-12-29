pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "CloudstreamPlugins"

// Incluye automáticamente todas las carpetas que contengan un build.gradle.kts
rootDir.listFiles()?.forEach { file ->
    if (file.isDirectory && File(file, "build.gradle.kts").exists() && file.name != "buildSrc") {
        include(":${file.name}")
    }
}
