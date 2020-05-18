package com.example.blog.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class QuerydslConfig (
        @PersistenceContext(unitName = "foo")
        @Qualifier(value = "entityManagerFactory")
        val fooEntityManager: EntityManager,

        @PersistenceContext(unitName = "bar")
        @Qualifier(value = "barEntityManagerFactory")
        val barEntityManager: EntityManager
) {

    @Bean
    fun fooJpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(fooEntityManager)
    }

    @Bean
    fun barJpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(barEntityManager)
    }
}