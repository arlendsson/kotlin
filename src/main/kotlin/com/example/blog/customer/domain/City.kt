package com.example.blog.customer.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "city")
data class City (
    /*
        city_id     smallint unsigned auto_increment
        primary key,
        city        varchar(50)                         not null,
        country_id  smallint unsigned                   not null,
        last_update timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
     */
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var city_id: Int,
    @Column(name = "city")
    var city: String,
//    @Column(name = "country_id")
//    var country_id: Int
    @Column(name = "last_update")
    var last_update: Date,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    var country: Country
)