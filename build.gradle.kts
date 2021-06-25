plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    maven("https://jitpack.io")  // For monun's libraries.
    maven("https://papermc.io/repo/repository/maven-public/") // PaperMC
}

dependencies {
    compileOnly(kotlin("stdlib")) // Kotlin
    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT") // Paper Latest
    implementation("com.github.monun:kommand:1.1.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "16" // 응 8로 내릴거야
    }
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
        filteringCharset = "UTF-8"
    }
    shadowJar {
        archiveClassifier.set("dist")
        archiveVersion.set("")
    }
    create<Copy>("dist") {
        from (shadowJar)
        into(".\\.server\\plugins")
    }
}
