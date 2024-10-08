package authentication

import common.KotlinClass

actual fun getJwtParser(): JwtParser {
    return KotlinClass.jwtParser
}