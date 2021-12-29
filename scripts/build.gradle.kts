plugins {
    id 'org.jetbrains.kotlin.jvm'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile ("org.jetbrains.kotlin:kotlin-script-runtime")
    compile ("com.github.holgerbrandl:kscript-annotations:1.2")
    implementation ("org.jline:jline:3.12.1")
    compile project(":")
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

sourceSets.main.java.srcDirs 'src'
