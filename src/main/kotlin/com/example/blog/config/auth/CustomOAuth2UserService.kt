package com.example.blog.config.auth

import com.example.blog.config.auth.dto.SessionUser
import com.example.blog.domain.user.User
import com.example.blog.domain.user.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService(
        val userRepository: UserRepository,
        val httpSession: HttpSession
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest)

        var registrationId: String = userRequest.clientRegistration.registrationId
        var userNameAttributeName: String = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        var attributes: OAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes as Map<String, Object>)

        var user: User = saveOrUpdate(attributes)

        httpSession.setAttribute("user", SessionUser(name = user.name, email = user.email, picture = user.picture))

        return DefaultOAuth2User(
                Collections.singleton(SimpleGrantedAuthority(user.getRoleKey())),
                attributes.attributes,
                attributes.nameAttributeKey
        )
    }

    fun saveOrUpdate(attributes: OAuthAttributes): User {
        var user: User = userRepository.findByEmail(attributes.email)?.let {
            it.update(attributes.name, attributes.picture)
        } ?: attributes.toEntity()
        return userRepository.save(user)
    }

}