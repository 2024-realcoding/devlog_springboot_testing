plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.com.cnu"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok") //TODO: annotation build 활성화 언급하기

    // db
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")

    testImplementation ("org.springframework.restdocs:spring-restdocs-mockmvc")
    // actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.opentelemetry:opentelemetry-api")

    implementation ("io.micrometer:micrometer-registry-prometheus")
    implementation ("com.slack.api:slack-api-client:1.29.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
