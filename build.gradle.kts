
// Root build.gradle.kts

plugins {
    // Keep the root lightweight; subprojects apply their own plugins
    base
}

group = "ai.neodatagroup.themis"
version = "1.0.0"

val javaVersion = JavaLanguageVersion.of(25) // change to 21 if you prefer

subprojects {
    // Every subproject gets these settings
    apply(plugin = "java") // common Java plugin (can be overridden per subproject)

    group = rootProject.group
    version = rootProject.version

    // Repositories
    repositories {
        mavenCentral()
    }

    // Use Gradle’s Java toolchain to ensure the right JDK is used everywhere
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(javaVersion)
        }
        // If you want to generate sources/javadoc jars for all libraries:
        // withSourcesJar()
        // withJavadocJar()
    }

    // Common Java compiler options
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        // Uncomment if you want warnings to be stricter:
        // options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
        // options.release.set(javaVersion.asInt()) // pins target compat to toolchain version
    }

    // Common jar manifest (optional)
    tasks.withType<Jar>().configureEach {
        manifest {
            attributes(
                "Implementation-Title" to project.name,
                "Implementation-Version" to (project.version.toString())
            )
        }
    }
}

// Aggregate tasks at the root (handy in IntelliJ’s Gradle tool window)
tasks.register("buildAll") {
    group = "build"
    description = "Build all Java subprojects"
    dependsOn(gradle.includedBuilds) // only relevant if using composite builds
    dependsOn(subprojects.map { it.tasks.named("build") })
}

tasks.register("cleanAll") {
    group = "build"
    description = "Clean all subprojects and root"
    dependsOn(subprojects.map { it.tasks.named("clean") })
    doLast {
        delete(".gradle", "build")
    }
}
