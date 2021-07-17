// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val androidGradleVersion by extra { "4.2.2" }
    val kotlinVersion by extra { "1.5.10" }
    val googleServiceVersion by extra { "4.3.8" }
    val navVersion by extra { "2.3.5" }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$androidGradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.gms:google-services:$googleServiceVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
