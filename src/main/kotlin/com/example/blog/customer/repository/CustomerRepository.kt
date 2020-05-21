package com.example.blog.customer.repository

import com.example.blog.customer.domain.*
import com.querydsl.jpa.impl.JPAQueryFactory
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
    override fun findList(offset: Long): List<Customer>? {
        return query
                .from(customer)
                .leftJoin(customer.address, address)
                .fetchJoin()
                .leftJoin(address.city, city)
                .fetchJoin()
                .leftJoin(city.country, country)
                .fetchJoin()
                .orderBy(customer.customer_id.asc())
//                .offset(offset).limit(10)
                .fetch() as List<Customer>?
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
    fun findList(offset: Long): List<Customer>?
    fun findByEmail(email: String): Customer?
    fun findByEmailCustomerDetail(email: String): Customer?
}