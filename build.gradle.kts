plugins {
    java
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    jacoco
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

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    test {
        useJUnitPlatform()
    }

    register<JacocoReport>("codeCoverageReport") {
        dependsOn(test)

        executionData.setFrom(fileTree(project.rootDir.absolutePath) {
            include("**/build/jacoco/*.exec")
        })

        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("$buildDir/reports/jacoco/report.xml"))
            html.required.set(false)
            csv.required.set(false)
        }

        sourceSets(sourceSets.main.get())
    }
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
