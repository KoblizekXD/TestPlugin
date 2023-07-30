plugins {
    kotlin("jvm") version "1.9.0"
    id("maven-publish")
    id("java-gradle-plugin")
    id("test-plugin") version "1.0-SNAPSHOT"
}

group = "lol.koblizek"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("test-plugin:test-plugin:1.0-SNAPSHOT")
    implementation(gradleApi())
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
gradlePlugin {
    plugins {
        create("test-plugin") {
            id = "test-plugin"
            implementationClass = "lol.koblizek.gradle.plugin.TestPlugin"
        }
    }
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "lol.koblizek"
            artifactId = "test-plugin"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}