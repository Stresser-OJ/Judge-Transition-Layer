package com.chosanglyul.jtl.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("file")
class File(
    @Id
    var id: Long? = null,

    val uuid: String,

    var name: String,
)
