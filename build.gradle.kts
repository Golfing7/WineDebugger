plugins {
    id("java")
}

val deployDirectory = "${System.getProperty("user.home")}/Servers/DoomPvP/plugins"
group = "com.golfing8"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    compileOnly("org.projectlombok:lombok:1.18.36")
    compileOnly("com.golfing8:KCommon:1.0")
    compileOnly("net.techcable.tacospigot:WineSpigot:1.8.8-R0.2-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.create("deploy") {
    dependsOn(tasks.jar)

    doFirst {
        val outputFile = tasks.getByName("jar").outputs.files.first()
        val targetFile = File(deployDirectory, "${project.name}-${project.version}.jar")

        outputFile.copyTo(targetFile, overwrite = true)
    }
}