package com.example.blog.domain.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners
abstract class BaseTimeEntity (
    @CreatedDate
    var createdDate: LocalDateTime,
    @LastModifiedDate
    var modifiedDate: LocalDateTime
)