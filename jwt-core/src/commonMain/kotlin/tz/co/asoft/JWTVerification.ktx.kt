package tz.co.asoft

fun JWT.jwtVerification(computedSignature: String?): JWTVerification {
    val jwtSignature = signature
    return if (computedSignature == jwtSignature) {
        JWTVerification.Valid(this)
    } else {
        JWTVerification.Invalid(jwtSignature = signature, computedSecret = computedSignature)
    }
}