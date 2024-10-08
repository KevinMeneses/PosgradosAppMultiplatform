package authentication

import com.auth0.android.jwt.JWT

actual fun getJwtParser() = object : JwtParser {
    override fun getClaim(token: String, key: String): String? {
        return JWT(token).getClaim(key).asString()
    }
}