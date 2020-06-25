package com.example.blog.domain.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User(
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var email: String,
        @Column
        var picture: String
) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
        @Enumerated(EnumType.STRING)
        @Column(nullable = false, columnDefinition = "ENUM('ROLE_GUEST', 'ROLE_USER')")
        var role: Role = Role.GUEST

//        override var createdDate: LocalDateTime,
//        override var modifiedDate: LocalDateTime

        fun update(name: String, picture: String): User {
                this.name = name
                this.picture = picture
                return this
        }

        fun getRoleKey(): String {
                return this.role.key
        }
}