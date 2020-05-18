package com.example.blog.customer.controller

import com.example.blog.customer.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(val customerRepository: CustomerRepository) {
    @GetMapping()
    fun getCustomers(): ResponseEntity<*> {
        val res = customerRepository.findAll()
        return ResponseEntity.ok(res)
    }
}