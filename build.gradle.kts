plugins {
    kotlin("jvm") version "1.6.10"
    `java-library`
}

description = "kotlin-inquirer"
group = "io.github.kotlin-inquirer"
version = 1.00

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jline:jline:3.21.0")
    implementation("org.fusesource.jansi:jansi:2.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

