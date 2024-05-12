plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.springframework.boot") version "3.2.5"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")

    implementation("com.github.ua-parser:uap-java:1.6.1")
}
