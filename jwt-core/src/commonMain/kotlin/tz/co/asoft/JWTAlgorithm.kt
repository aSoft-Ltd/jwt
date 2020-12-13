package tz.co.asoft

import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.days

open class JWTAlgorithm(
    val name: String,
    private val rotator: KeyRotator,
    private val signer: JWTSigner
) : JWTSigner by signer {
    @OptIn(ExperimentalTime::class)
    open suspend fun createJWT(builder: JWTPayload.() -> Unit): JWT {
        val key = rotator.nextSigningKey()

        val header = JWTHeader {
            alg = name
            key.uid?.let {
                kid = it
            }
            typ = "JWT"
        }

        val payload = JWTPayload {
            builder()
            iat = Clock.System.now().toEpochMilliseconds()
            exp = (Clock.System.now() + 1.days).toEpochMilliseconds()
        }
        return sign(JWT(header, payload), key)
    }
}