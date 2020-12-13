@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

data class JWT(
    val header: JWTHeader,
    val payload: JWTPayload,
    val signature: String? = null
) {
    companion object {
        fun parse(token: String): JWT {
            val splits = token.split(".")
            val h = splits[0]
            val p = splits[1]
            val signature = splits.getOrNull(2)
            val header = Base64.decode(h).ascii
            val payload = Base64.decode(p).ascii
            return JWT(
                header = Mapper.decodeFromString(header).mapValues { (_, it) -> it.toString() }
                    .toMutableMap(),
                payload = Mapper.decodeFromString(payload).toMutableMap(),
                signature = signature
            )
        }

        fun parseOrNull(token: String): JWT? = try {
            parse(token)
        } catch (_: Throwable) {
            null
        }
    }
}