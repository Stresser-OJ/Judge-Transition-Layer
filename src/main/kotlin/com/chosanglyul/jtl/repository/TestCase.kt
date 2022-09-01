package com.chosanglyul.jtl.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("testcase")
class TestCase(
    @Id
    var id: Long? = null,

    val problemId: Long,

    val testCaseId: Long,

    val fileId: Long,

    val genCmd: String? = null,
)
