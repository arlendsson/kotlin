package com.example.blog.customer.controller

import com.example.blog.customer.domain.Customer
import com.example.blog.customer.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(val repository: CustomerRepository) {

    @GetMapping()
    fun getCustomers(): ResponseEntity<List<Customer>> {
        val res = repository.findList(10)
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