plugins {
    val agp = "8.0.2"
    val kotlin = "1.8.20"
    val compose = "1.4.0"

    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(agp).apply(false)
    id("com.android.library").version(agp).apply(false)
    kotlin("android").version(kotlin).apply(false)
    kotlin("multiplatform").version(kotlin).apply(false)
    id("org.jetbrains.compose").version(compose).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
