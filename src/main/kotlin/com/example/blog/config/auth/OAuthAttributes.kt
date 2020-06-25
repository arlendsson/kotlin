package com.example.blog.config.auth

import com.example.blog.domain.user.User

class OAuthAttributes(
        var attributes: Map<String, Object>,
        var nameAttributeKey: String,
        var name: String,
        var email: String,
        var picture: String
) {
    companion object {
        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Object>): OAuthAttributes {
            if ("naver".equals(registrationId)) {
                return ofNaver("id", attributes)
            }
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Object>): OAuthAttributes {
            return OAuthAttributes(
                    name = attributes.get("name") as String,
                    email = attributes.get("email") as String,
                    picture = attributes.get("profile_image") as String,
                    attributes =  attributes,
                    nameAttributeKey = userNameAttributeName
                    )
        }
        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Object>): OAuthAttributes {
            return OAuthAttributes(
                    name = attributes.get("name") as String,
                    email = attributes.get("email") as String,
                    picture = attributes.get("picture") as String,
                    attributes =  attributes,
                    nameAttributeKey = userNameAttributeName
                    )
        }
    }

    fun toEntity(): User {
        return User(name = name, email = email, picture = picture)
    }
}