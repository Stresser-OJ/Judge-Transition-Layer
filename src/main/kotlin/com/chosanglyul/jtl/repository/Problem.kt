package com.chosanglyul.jtl.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("problem")
class Problem {
    @Id
    var id: Long? = null
}
