package com.example.blog

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Article(
		var title: String,
		var headline: String,
		var content: String,
		@ManyToOne var author: Userr,
		var slug: String = title.toSlug(),
		var addedAt: LocalDateTime = LocalDateTime.now(),
		@Id @GeneratedValue var id: Long? = null)

@Entity
class Userr(
		var login: String,
		var firstname: String,
		var lastname: String,
		var description: String? = null,
		@Id @GeneratedValue var id: Long? = null)
