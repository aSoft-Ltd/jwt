package tz.co.asoft

class HS256Algorithm(rotator: KeyRotator) : JWTAlgorithm(NAME, rotator, HS256Signer) {
    companion object {
        const val NAME = "HS256"
    }

    constructor(secret: String) : this(LinearKeyRotator(
        maxKeys = 1,
        source = InMemoryKeySource<SecurityKey>(),
        generator = { SecurityKey(uid = "0", value = secret) }
    ))
}