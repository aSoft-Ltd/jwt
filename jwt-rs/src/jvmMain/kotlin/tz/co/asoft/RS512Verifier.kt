package tz.co.asoft

import java.security.Signature

fun JWT.verifyRS512(key: SecurityKey): JWTVerification {
    val publicKey = key.toRSAPublicKey()
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