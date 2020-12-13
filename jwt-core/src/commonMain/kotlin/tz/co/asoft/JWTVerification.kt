package tz.co.asoft

sealed class JWTVerification {
    class Invalid(val jwtSignature: String?, val computedSecret: String?) : JWTVerification()
    class Valid(val jwt: JWT) : JWTVerification()
}