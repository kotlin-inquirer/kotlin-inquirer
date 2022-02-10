plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.github.kotlin-inquirer"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":"))
}

tasks {
    shadowJar {
        archiveBaseName.set("kotlin-pizza")
        archiveClassifier.set("")
        archiveVersion.set("")
        manifest {
            attributes(Pair("Main-Class", "PizzaKt"))
        }
    }
}
