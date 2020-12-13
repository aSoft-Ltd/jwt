# JWT
![badge][badge-maven] ![badge][badge-mpp] ![badge][badge-android] ![badge][badge-js] ![badge][badge-jvm]

A kotlin multiplatform library to handle working with jwt

## Samples
```kotlin
val alg1 = HS256Algorithm("wakubwawenu")
val jwt = alg1.createJWT {
    uid = "55"
    aid = "55"
    accountName = "anderson"
    userName = "anderson"
}
val token = jwt.token()
jwt.verifyHS256(key1)
```
## Setup:Gradle
```kotlin
dependencies {
    implementation("tz.co.asoft:jwt-core:+") // please use the latest version possible
    implementation("tz.co.asoft:jwt-hs:+") // please use the latest version possible
    implementation("tz.co.asoft:jwt-rs:+") // please use the latest version possible
}
```

[badge-maven]: https://img.shields.io/maven-central/v/tz.co.asoft/test/1.0.1?style=flat
[badge-mpp]: https://img.shields.io/badge/kotlin-multiplatform-blue?style=flat
[badge-android]: http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat
