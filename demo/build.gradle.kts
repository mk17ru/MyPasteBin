import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.moowork.node") version "1.3.1"
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	kotlin("plugin.jpa") version "1.4.32"
}

group = "com.pastebin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11


repositories {
	mavenCentral()
}

node {
	download = true
}

dependencies {
	implementation("javax.servlet", "javax.servlet-api", "4.0.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	compile("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.session:spring-session-core")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.boot", "spring-boot-starter-thymeleaf", "2.4.5")
	implementation("org.postgresql", "postgresql", "42.1.4")
	compile("com.h2database:h2")
	implementation("org.hibernate.validator", "hibernate-validator", "7.0.1.Final")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

}


