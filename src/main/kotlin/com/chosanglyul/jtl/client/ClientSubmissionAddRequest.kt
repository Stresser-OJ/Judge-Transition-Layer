package com.chosanglyul.jtl.client

data class ClientSubmissionAddRequest(
    val source_code: String,
    val language_id: Long,
    val stdin: String? = null,
    val expected_output: String? = null,
    val cpu_time_limit: Float,
    val memory_limit: Float,
)
