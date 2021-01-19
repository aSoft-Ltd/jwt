package tz.co.asoft

import java.security.Signature
import java.security.interfaces.RSAPublicKey

fun JWT.verifyRS512(key: SecurityKey): JWTVerification = verifyRS512(key.toRSAPublicKey())

fun JWT.verifyRS512(publicKey: RSAPublicKey): JWTVerification {
    val signature = this.signature ?: return JWTVerification.Invalid(null, null)
    val sig = Signature.getInstance(RS512Algorithm.ALGO_NAME)
    sig.initVerify(publicKey)
    sig.update(message)
    return if (sig.verify(Base64.decode(signature))) {
        JWTVerification.Valid(this)
    } else {
        JWTVerification.Invalid(signature, null)
    }
}