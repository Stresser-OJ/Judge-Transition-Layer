package com.chosanglyul.jtl.client

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientSubmissionAddRequest(
    @JsonProperty("source_code")
    val sourceCode: String,

    @JsonProperty("language_id")
    val languageId: Long,

    @JsonProperty("stdin")
    val stdin: String? = null,

    @JsonProperty("expected_output")
    val expectedOutput: String? = null,

    @JsonProperty("cpu_time_limit")
    val cpuTimeLimit: Float,

    @JsonProperty("memory_limit")
    val memoryLimit: Float,
)
