package com.example.blog.customer.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
//        address_id	smallint unsigned
//        address	varchar(50)
//        address2	varchar(50)
//        district	varchar(20)
//        city_id	smallint unsigned
//        postal_code	varchar(10)
//        phone	varchar(20)
//        location	geometry
//        last_update	timestamp
        @Id
        @Column(name = "address_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var address_id: Int = 0,
        @Column(name = "address")
        var address: String = "",
        @Column(name = "address2")
        var address2: String = "",
        @Column(name = "district")
        var district: String = "",
//        @Column(name = "city_id")
//        var city_id: Int = 0,
        @Column(name = "postal_code")
        var postal_code: String = "",
        @Column(name = "phone")
        var phone: String = "",
        @Column(name = "location")  // MySQL geometry type
        var location: String = "",
        @Column(name = "last_update")
        var last_update: Date = Date(),
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "city_id")
        var city: City
)