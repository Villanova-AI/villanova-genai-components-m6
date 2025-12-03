plugins {
    id("org.springframework.boot") version "3.5.8"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.vaadin") version "24.9.5"
    id("org.openapi.generator") version "7.8.0"
    java
}

description = "The bot component of the Themis recommender system"


dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.8")
        mavenBom("com.vaadin:vaadin-bom:24.9.5")
    }
}

dependencies {
    // Yaml
    implementation("org.yaml:snakeyaml")

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-core:2.19.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.4")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.19.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.4")

    // Vaadin
    implementation("com.vaadin:vaadin-spring-boot-starter")
    implementation("com.vaadin:vaadin-core")

    // Flexmark
    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")

    // Provided
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
}


// Spring Boot main class
springBoot {
    mainClass.set("ai.neodatagroup.themis.bot.ThemisBotApplication")
}


openApiGenerate {
    generatorName.set("java")
    inputSpec.set("${rootProject.projectDir}/themis-openapi/openapi.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").map { it.asFile.absolutePath })
    apiPackage.set("ai.neodatagroup.themis.client.api")
    modelPackage.set("ai.neodatagroup.themis.client.model")
    typeMappings.set(mapOf("OffsetDateTime" to "java.time.Instant"))
    configOptions.set(
        mapOf(
            "library" to "native",
            "dateLibrary" to "java8",
            "useJakartaEe" to "true",
            "hideGenerationTimestamp" to "true",
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
