package tz.co.asoft

fun JWT.verifyHS256(key: SecurityKey) = jwtVerification(
    computedSignature = hs256Sign(this, key.value).signature
)