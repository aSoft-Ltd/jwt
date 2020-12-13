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
                api(project(":jwt-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test", vers.asoft.test))
            }
        }
    }
}
