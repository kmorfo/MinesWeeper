import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.navigation.compose)

            implementation(libs.koin.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)

            // DataStore
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
        }
        iosMain.dependencies {

        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
    // Added to prevent a build error when using Android Studio "Make" button
    task("testClasses")

    // Test and improving performance
    composeCompiler {
        reportsDestination = file("build/outputs/compose_reports")
        metricsDestination = file("build/outputs/compose_metrics")
//        stabilityConfigurationFile = file("/$rootDir/stability-config.txt")
//        Its enable by default on kotlin 2.0.20
//        enableStrongSkippingMode = true
    }
}

android {
    namespace = "es.rlujancreations.minesweeper"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "es.rlujancreations.minesweeper"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.android.versionCode.get().toInt()
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
dependencies {
    testImplementation(libs.junit)
}
compose.desktop {
    application {
        mainClass = "es.rlujancreations.minesweeper.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Deb, TargetFormat.Msi)
            packageName = "es.rlujancreations.minesweeper"
            version = "1.0.0"

            macOS {
                iconFile.set(project.file("resources/icon.icns"))
            }

//            windows{
//                iconFile.set(project.file("resources/icon.ico"))
//            }
        }
    }
}
