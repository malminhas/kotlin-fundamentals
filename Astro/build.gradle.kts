plugins {
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("test-common"))
    implementation(kotlin("test-junit"))

    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.code.gson:gson:2.8.6")
    //implementation("commons-validator:commons-validator:1.6")
    implementation("io.ktor:ktor-client-gson:1.4.0")
}
