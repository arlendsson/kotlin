package com.example.blog.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}