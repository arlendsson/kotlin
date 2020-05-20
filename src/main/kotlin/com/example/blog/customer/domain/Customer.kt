package com.example.blog.customer.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
//        customer_id	smallint unsigned
//        store_id	    tinyint unsigned
//        first_name	varchar(45)
//        last_name	    varchar(45)
//        email	        varchar(50)
//        address_id	smallint unsigned
//        active	    tinyint(1)
//        create_date	datetime
//        last_update	timestamp
        @Id
        @Column(name = "customer_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var customer_id: Int = 0,
        @Column(name = "store_id")
        var store_id: Int = 0,
        @Column(name = "first_name")
        var first_name: String = "",
        @Column(name = "last_name")
        var last_name: String = "",
        @Column(name = "email")
        var email: String = "",
//        @Column(name = "address_id")
//        var address_id: Int = 0,
        @Column(name = "active")
        var active: Int = 0,
        @Column(name = "create_date")
        var create_date: Date = Date(),
        @Column(name = "last_update")
        var last_update: Date = Date(),

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "address_id")
        var address: Address
)