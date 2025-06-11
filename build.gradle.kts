val exposedVersion: String by project

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()

}



dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //implementation("com.mysql:mysql-connector-j:9.3.0")

    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:${exposedVersion}")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    //implementation("org.jetbrains.exposed:exposed-migration:$exposedVersion")


//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.oracle.database.jdbc:ojdbc11")
    // db migrations

    //UUID
    implementation("com.github.f4b6a3:uuid-creator:6.1.1")

    implementation("org.flywaydb:flyway-core")
    //implementation("org.flywaydb:flyway-mysql")
    implementation("org.flywaydb:flyway-database-oracle")


    developmentOnly("org.springframework.boot:spring-boot-devtools")
    //runtimeOnly("com.h2database:h2")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //testRuntimeOnly("com.h2database:h2")


}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
