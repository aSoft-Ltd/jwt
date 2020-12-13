import tz.co.asoft.JWT
import tz.co.asoft.SecurityKey
import tz.co.asoft.jwtVerification

fun JWT.verifyTSTALG(key: SecurityKey) = jwtVerification(
    computedSignature = TestSigner.sign(this, key).signature
)