package com.example.blog

import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
	fun findBySlug(slug: String): Article?
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserrRepository : CrudRepository<Userr, Long> {
	fun findByLogin(login: String): Userr?
}

