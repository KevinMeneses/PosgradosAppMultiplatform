package authentication

interface JwtParser {
    fun getClaim(token: String, key: String): String?
}

expect fun getJwtParser(): JwtParser