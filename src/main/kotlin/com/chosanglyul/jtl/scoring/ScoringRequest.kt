package com.chosanglyul.jtl.scoring

data class SubmissionAddRequest(
    val source_code: String,
    val language: String,
    val stdin: String,
    val expected_output: String? = null,
    val cpu_time_limit: Float,
    val memory_limit: Float,
)