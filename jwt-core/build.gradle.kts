plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

repositories {
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

kotlin {
    universalLib()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft("krypto-core", vers.asoft.krypto))
                api("org.jetbrains.kotlinx:kotlinx-datetime:${vers.kotlinx.datetime}")
                api(asoft("kotlinx-serialization-mapper", vers.asoft.mappers))
                api(asoft("krypto-keys", vers.asoft.krypto))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test",vers.asoft.test))
            }
        }
    }
}
