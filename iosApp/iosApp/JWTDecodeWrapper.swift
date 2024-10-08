//
//  JWTDecodeWrapper.swift
//  iosApp
//
//  Created by Kevin Meneses on 7/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import composeApp
import JWTDecode

class JWTDecodeWrapper: JwtParser {
    func getClaim(token: String, key: String) -> String? {
        do {
            let jwt = try decode(jwt: token)
            return jwt.claim(name: key).string
        } catch {
            print("Error decoding token: \(error)")
            return nil
        }
    }
}
