plugins {
    kotlin("jvm") version "1.6.10"
    `java-library`
}

description = "kotlin-inquirer"
group = "com.github.kotlin-inquirer"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jline:jline:3.21.0")
    implementation("org.fusesource.jansi:jansi:2.4.0")
    testImplementation(kotlin("test"))
}
kotlin {
    explicitApi()
}

tasks.test {
    useJUnitPlatform()
}

