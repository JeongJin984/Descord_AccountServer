package com.descord.accountserver.data.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class Account (
    @Id @Column("id") val id: Long,
    @Column("name") val name: String
) {

}