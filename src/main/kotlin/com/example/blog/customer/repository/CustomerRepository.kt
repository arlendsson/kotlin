package com.example.blog.customer.repository

import com.example.blog.customer.domain.*
import com.querydsl.core.QueryResults
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

//@Repository
@Transactional
class CustomerRepositoryImpl(
        @Resource(name = "jpaQueryFactory")
        val query: JPAQueryFactory
): QuerydslRepositorySupport(Customer::class.java), CustomerCustomizedRepository {
    val customer: QCustomer = QCustomer.customer
    val address: QAddress = QAddress.address1
    val city: QCity = QCity.city1
    val country: QCountry = QCountry.country1

    @EntityGraph(attributePaths = ["address"])
    override fun findList(pageable: Pageable, param: Customer): Page<Customer>? {
        val result: QueryResults<Customer> = query
                .from(customer)
                .leftJoin(customer.address, address)
                .fetchJoin()
                .leftJoin(address.city, city)
                .fetchJoin()
                .leftJoin(city.country, country)
                .fetchJoin()
                .orderBy(customer.customer_id.asc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetchResults() as QueryResults<Customer>

        return PageImpl<Customer>(result.results, pageable, result.total)
    }

    override fun findByEmail(email: String): Customer? {
        return query
                .selectFrom(customer)
                .where(customer.email.eq(email))
                .fetchOne()
    }

    override fun findByEmailCustomerDetail(email: String): Customer? {
        return query
                .from(customer)
                .leftJoin(customer.address, address)
                .fetchJoin()
                .leftJoin(address.city, city)
                .fetchJoin()
                .leftJoin(city.country, country)
                .fetchJoin()
                .where(customer.email.eq(email))
                .fetchOne() as Customer?
    }
}

interface CustomerRepository: JpaRepository<Customer, String>, CustomerCustomizedRepository {
}

interface CustomerCustomizedRepository {
    fun findList(pageable: Pageable, param: Customer): Page<Customer>?
    fun findByEmail(email: String): Customer?
    fun findByEmailCustomerDetail(email: String): Customer?
}