package com.example.blog.customer.controller

import com.example.blog.customer.domain.Customer
import com.example.blog.customer.repository.CustomerRepository
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(val repository: CustomerRepository) {

    val log = KotlinLogging.logger {}

    @GetMapping()
    fun getCustomers(pageable: Pageable, param: Customer): ResponseEntity<Page<Customer>> {
        log.info("##### pageable: {}", pageable.toString())
        log.info("##### param: {}", param.toString())

        val res = repository.findList(pageable, param)

        return ResponseEntity(res, HttpStatus.OK)
    }

    @GetMapping("/{email}")
    fun getCustomer(@PathVariable("email") email: String): ResponseEntity<Customer> {
//        if (email.isEmpty()) {
//            email = "PATRICIA.JOHNSON@sakilacustomer.org"
//        }
        val res = repository.findByEmailCustomerDetail(email)
//        val res = repository.findByEmail(email)

        return ResponseEntity(res, HttpStatus.OK)
    }
}