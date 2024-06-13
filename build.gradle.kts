plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "com.linuxgods.kreiger.intellij.idea.www-form-urlencoded"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDirs("build/generated/lexer", "build/generated/parser" )
        }
    }
}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2024.1.2")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf( "PsiViewer:241.14494.158-EAP-SNAPSHOT"))
}

tasks {
    generateLexer {
        sourceFile.set(file("src/main/grammars/UrlEncoded.flex"))
        targetOutputDir.set(file("build/generated/lexer"))
    }
    generateParser {
        sourceFile.set(file("src/main/grammars/UrlEncoded.bnf"))
        targetRootOutputDir.set(file("build/generated/parser"))
        pathToParser.set("com/linuxgods/kreiger/intellij/idea/url/encoded/parser/UrlEncodedParser.java")
        pathToPsiRoot.set("com/linuxgods/kreiger/intellij/idea/url/encoded/psi/")
        purgeOldFiles.set(true)
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        dependsOn(generateParser, generateLexer)
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
