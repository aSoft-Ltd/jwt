package tz.co.asoft

class HS256Verifier(private val secret: String) : JWTVerifier {
    constructor(key: SecurityKey) : this(key.value)

    override fun verify(jwt: JWT): JWTVerification = jwt.jwtVerification(
        computedSignature = hs256Sign(jwt, secret).signature
    )
}