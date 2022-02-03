plugins {
    java
    kotlin("jvm") version "1.6.10"
    `maven-publish`
}

description = "kotlin-inquirer"
group = "com.github.kotlin-inquirer"
version = "0.1.0"

repositories {
    mavenCentral()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
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


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.kotlin-inquirer"
            artifactId = "kotlin-inquirer"
            version = "0.1.0"

            from(components["java"])
        }
    }
}

