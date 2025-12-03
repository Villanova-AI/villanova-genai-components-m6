plugins {
    id("org.springframework.boot") version "3.5.8"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.openapi.generator") version "7.8.0"
    java
}

description = "The server of the Themis recommender system"

dependencies {
    // Spring Boot starters
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Database driver
    runtimeOnly("org.postgresql:postgresql")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // OpenAPI / Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")
}

// Spring Boot main class
springBoot {
    mainClass.set("ai.neodatagroup.themis.server.ThemisServerApplication")
}


openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("${rootProject.projectDir}/themis-openapi/openapi.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").get().asFile.path)
    apiPackage.set("ai.neodatagroup.themis.server.api")
    modelPackage.set("ai.neodatagroup.themis.server.model")
    typeMappings.set(mapOf("OffsetDateTime" to "java.time.Instant"))
    ignoreFileOverride.set(layout.projectDirectory.file(".openapi-generator-ignore").asFile.absolutePath)
    configOptions.set(
        mapOf(
            "basePackage" to "ai.neodatagroup.themis.server",
            "configPackage" to "ai.neodatagroup.themis.server.config",
            "useSpringBoot3" to "true",
            "interfaceOnly" to "false",
            "delegatePattern" to "true",
            "useTags" to "true",
            "useOptional" to "true",
            "openApiNullable" to "false"
        )
    )
}

sourceSets["main"].java {
    srcDir(layout.buildDirectory.dir("generated/src/main/java"))
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}
