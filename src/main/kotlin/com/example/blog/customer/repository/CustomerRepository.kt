package com.example.blog.customer.repository

import com.example.blog.customer.domain.Customer
import com.example.blog.customer.domain.QCustomer
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class CustomerRepositorySupport(
        @Resource(name = "jpaQueryFactory")
        val query: JPAQueryFactory
): QuerydslRepositorySupport(Customer::class.java) {
    fun findByEmail(email: String): Customer? {
        return query.selectFrom(QCustomer.customer)
                .where(QCustomer.customer.email.eq(email))
                .fetchOne()
    }
}

//interface CustomerRepository: JpaRepository<Customer, String> {
//    fun findByEmail(email: String): Customer?
//}