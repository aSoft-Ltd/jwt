import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ParserTest {
    val algName = "TSTALG"
    val key = SecurityKey(value = "testkey")

    @Test
    fun should_fail_to_parse() {
        val jwt = JWT.parseOrNull("")
        assertNull(jwt)
    }

    @Test
    fun can_stringify_claims() = asyncTest {
        val rotator = LinearKeyRotator(maxKeys = 1) { key }
        val alg = JWTAlgorithm(algName, rotator, TestSigner)
        val jwt = alg.createJWT {
            uid = "0"
            claims = mapOf(
                "test" to listOf(
                    "me", "myself", "i"
                )
            )
        }
        println(jwt)
        println(jwt.token())
        assertEquals(algName, jwt.header.alg)
    }

    @Test
    fun can_parse_a_known_token() {
        val token =
            "eyJhbGciOiJUU1RBTEciLCJ0eXAiOiJKV1QiLCJraWQiOiIwIn0.eyJ1aWQiOjAsImNsYWltcyI6WyJtZSIsIm15c2VsZiIsImkiXX0.ZXlKaGJHY2lPaUpVVTFSQlRFY2lMQ0owZVhBaU9pSktWMVFpTENKcmFXUWlPakI5LmV5SjFhV1FpT2pBc0ltTnNZV2x0Y3lJNld5SnRaU0lzSW0xNWMyVnNaaUlzSW1raVhTd2lhV0YwSWpwdWRXeHNMQ0psZUhBaU9tNTFiR3g5LmRHVnpkR3RsZVE"
        val jwt = JWT.parse(token)
        assertEquals(algName, jwt.header.alg)
        assertEquals(token, jwt.token())
    }

    @Test
    fun should_a_list_of_claims() {
        val token =
            "eyJhbGciOiJUU1RBTEciLCJ0eXAiOiJKV1QiLCJraWQiOiIwIn0.eyJ1aWQiOiIwIiwiY2xhaW1zIjp7InRlc3QiOlsibWUiLCJteXNlbGYiLCJpIl19LCJpYXQiOjE2MTIzNTkyMzQ3OTMsImV4cCI6MTYxMjQ0NTYzNDc5M30.ZXlKaGJHY2lPaUpVVTFSQlRFY2lMQ0owZVhBaU9pSktWMVFpTENKcmFXUWlPaUl3SW4wLmV5SjFhV1FpT2lJd0lpd2lZMnhoYVcxeklqcDdJblJsYzNRaU9sc2liV1VpTENKdGVYTmxiR1lpTENKcElsMTlMQ0pwWVhRaU9qRTJNVEl6TlRreU16UTNPVE1zSW1WNGNDSTZNVFl4TWpRME5UWXpORGM1TTMwLmRHVnpkR3RsZVE"
        val jwt = JWT.parse(token)
        val claims = jwt.payload.claims
        assertEquals(1, claims.size)
    }
}