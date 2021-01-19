package tz.co.asoft

import java.security.interfaces.RSAPublicKey

class RS512Verifier(private val publicKey: RSAPublicKey) : JWTVerifier {
    constructor(key: SecurityKey) : this(key.toRSAPublicKey())
    constructor(key: String) : this(SecurityKey(value = key))

    override fun verify(jwt: JWT): JWTVerification = jwt.verifyRS512(publicKey)
}