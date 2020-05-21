package com.example.blog.customer.domain

import javax.persistence.*

@Entity
@Table(name = "country")
class Country (
    /*
    country_id  smallint unsigned auto_increment
        primary key,
    country     varchar(50)                         not null,
    last_update timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
     */
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var country_id: Int,
    @Column(name = "country")
    var country: String
)