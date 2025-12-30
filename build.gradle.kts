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

// Configuración de repositorio obligatoria en la raíz
cloudstream {
    setRepo("https://github.com/DamianKing12/Indexadores2")
}

// Forzamos a que el sistema busque en los subproyectos
subprojects {
    apply(plugin = "com.lagradost.cloudstream3.gradle")
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
