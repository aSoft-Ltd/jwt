import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HS256AlgorithmTest {

    @Test
    fun should_create_a_jwt_with_HS256_algorithm() = asyncTest {
        val alg1 = HS256Algorithm("wakubwawenu")
        val jwt = alg1.createJWT {
            uid = "55"
            aid = "55"
            accountName = "anderson"
            userName = "anderson"
        }
        val token = jwt.token()
        println(jwt)
        println(token)
        assertEquals(3, token.split(".").size)
    }

    @Test
    fun should_verify_without_having_algorithm_instance() {
        val token1 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjAifQ.eyJ1aWQiOiI1NSIsImFpZCI6IjU1IiwiYWNjb3VudE5hbWUiOiJhbmRlcnNvbiIsInVzZXJOYW1lIjoiYW5kZXJzb24iLCJpYXQiOjE2MDc4MzkwMjM1MzAsImV4cCI6MTYwNzkyNTQyMzUzMH0.DnwbeGo3u26UsXrRpGSqKLpRHLtWdDQ5S3xwJvvk2FY"""
        val jwt1 = JWT.parse(token1)
        assertEquals(token1, jwt1.token())
        assertEquals("HS256", jwt1.header.alg)
        val key1 = SecurityKey(uid = "1", value = "secret")
        assertTrue(jwt1.verifyHS256(key1) is JWTVerification.Invalid)
        val key2 = SecurityKey(uid = "2", value = "wakubwawenu")
        assertTrue(jwt1.verifyHS256(key2) is JWTVerification.Valid, "Token $token1 ain't valid invalid")
        val token2 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjAifQ.eyJ1aWQiOiI1NSIsImFpZCI6IjU1IiwiYWNjb3VudE5hbWUiOiJhbmRlcnNvbiIsInVzZXJOYW1lIjoiYW5kZXJzb24iLCJpYXQiOjE2MDc4MzY4NzIyMzQsImV4cCI6MTYwNzkyMzI3MjIzNH0._UcEFyvNnnhGEVJeuivFPYK1znLSf5sjHFbGNrFmrhd"""
        val jwt2 = JWT.parse(token2)
        println(jwt2.payload)
        assertTrue(jwt2.verifyHS256(key2) is JWTVerification.Invalid, "Token is invalid")
    }

    @Test
    fun should_verify_with_verifier() {
        val token1 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjAifQ.eyJ1aWQiOiI1NSIsImFpZCI6IjU1IiwiYWNjb3VudE5hbWUiOiJhbmRlcnNvbiIsInVzZXJOYW1lIjoiYW5kZXJzb24iLCJpYXQiOjE2MDc4MzkwMjM1MzAsImV4cCI6MTYwNzkyNTQyMzUzMH0.DnwbeGo3u26UsXrRpGSqKLpRHLtWdDQ5S3xwJvvk2FY"""
        val jwt1 = JWT.parse(token1)
        assertEquals(token1, jwt1.token())
        assertEquals("HS256", jwt1.header.alg)
        val verifier1 = HS256Verifier("secret")
        assertTrue(verifier1.verify(jwt1) is JWTVerification.Invalid)
        val verifier2 = HS256Verifier("wakubwawenu")
        assertTrue(verifier2.verify(jwt1) is JWTVerification.Valid, "Token $token1 ain't valid invalid")
        val token2 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjAifQ.eyJ1aWQiOiI1NSIsImFpZCI6IjU1IiwiYWNjb3VudE5hbWUiOiJhbmRlcnNvbiIsInVzZXJOYW1lIjoiYW5kZXJzb24iLCJpYXQiOjE2MDc4MzY4NzIyMzQsImV4cCI6MTYwNzkyMzI3MjIzNH0._UcEFyvNnnhGEVJeuivFPYK1znLSf5sjHFbGNrFmrhd"""
        val jwt2 = JWT.parse(token2)
        println(jwt2.payload)
        assertTrue(verifier2.verify(jwt2) is JWTVerification.Invalid, "Token is invalid")
    }
}