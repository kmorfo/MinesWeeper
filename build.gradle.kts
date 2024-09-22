import org.gradle.kotlin.dsl.ktlint
import org.gradle.kotlin.dsl.libs
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktlint)
}

ktlint {
    android = true
    reporters {
        reporter(ReporterType.CHECKSTYLE)
//        reporter(ReporterType.HTML)
//        reporter(ReporterType.PLAIN)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
