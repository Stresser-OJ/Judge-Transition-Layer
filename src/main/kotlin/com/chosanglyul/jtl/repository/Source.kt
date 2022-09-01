package com.chosanglyul.jtl.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("source")
class Source(
    @Id
    var id: Long? = null,

    var type: SourceType = SourceType.NONE,

    var problemId: Long,

    var fileId: Long,
)
