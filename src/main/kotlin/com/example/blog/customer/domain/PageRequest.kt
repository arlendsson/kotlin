package com.example.blog.customer.domain

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

//class PageRequest {   // ERROR
//    var page: Int = 0
//    get() = field
//    set (value) {
//        field = if (page <= 0) {
//                        1
//                    } else {
//                        page
//                    }
//        }
//    }
//
//    var size: Int = 0
//    get() = field
//    set (value) {
//        val default_size: Int = 10
//        val max_size: Int = 50
//        field = if (size > max_size) {
//                        default_size
//                    } else {
//                        size
//                    }
//    }
//    lateinit var direction: Sort.Direction
//
//    fun of(): PageRequest {
//        return PageRequest.of(page -1, size, direction, "create_date")
//    }
//}