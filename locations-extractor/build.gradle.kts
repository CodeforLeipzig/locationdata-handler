group = "de.codefor.le.locations.extractor"
version = "1.0.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm")
    idea
    id("org.springframework.boot")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    implementation("com.fasterxml.jackson.core:jackson-core:_")
    implementation("com.fasterxml.jackson.core:jackson-annotations:_")
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:_")
    implementation("com.github.victools:jsonschema-generator:_")
    implementation("com.github.jasminb:jsonapi-converter:_")
    implementation(platform(Spring.boms.dependencies))

    implementation("org.springframework.boot:spring-boot-starter:_")
    implementation(Spring.boot.web)
    implementation("edu.stanford.nlp:stanford-corenlp:_")
   implementation("com.google.guava:guava:_")

    testImplementation(Spring.boot.test)
	  testImplementation(Testing.junit.jupiter.api)
    testRuntimeOnly(Testing.junit.jupiter.engine)
    implementation(kotlin("stdlib"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"

        freeCompilerArgs += listOf("-Xuse-ir")
    }
}

application {
    mainClass.set("de.codefor.le.locations.extractor.LocationsExtractor")
}

