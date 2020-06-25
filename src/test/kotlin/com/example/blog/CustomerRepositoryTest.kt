package com.example.blog

//import com.example.blog.customer.repository.CustomerRepositorySupport
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
//
////import org.junit.runner.RunWith
//
////@RunWith(SpringRunner::class)
//@SpringBootTest
//class CustomerRepositoryTest {
//
//    @Autowired
//    lateinit var customerRepositorySupport: CustomerRepositorySupport
//
//    @Test
//    fun `이메일로 고객 찾기`() {
//        val res = customerRepositorySupport.findByEmail("MARY.SMITH@sakilacustomer.org")
//        println("##### res:" + res)
//        assertEquals("SMITH", res?.last_name)
//    }
//}