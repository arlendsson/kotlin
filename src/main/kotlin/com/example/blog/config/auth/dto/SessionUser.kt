package com.example.blog.config.auth.dto

import java.io.Serializable

class SessionUser(
        var name: String,
        var email: String,
        var picture: String
): Serializable {

}