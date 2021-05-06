import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.compose.compose

plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.compose") version "0.4.0-build188"
}

group = "me.nino"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-RC")
    implementation(compose.desktop.currentOs)
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.11.0.202103091610-r")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}