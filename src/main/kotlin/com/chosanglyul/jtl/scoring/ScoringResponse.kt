package com.chosanglyul.jtl.scoring

import com.fasterxml.jackson.annotation.JsonProperty

data class SubmissionAddResponse(
    @JsonProperty("token")
    val token: String,
)

data class SubmissionRunResponse(
    @JsonProperty("stdout")
    val stdout: String? = null,
    @JsonProperty("stderr")
    val stderr: String? = null,
    @JsonProperty("compile_output")
    val compile_output: String? = null,
    @JsonProperty("message")
    val message: String? = null,
    @JsonProperty("status")
    val status: SubmissionStatus,
    @JsonProperty("token")
    val token: String,
    @JsonProperty("time")
    val time: Float,
    @JsonProperty("memory")
    val memory: Float,
)

data class SubmissionStatus(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("description")
    val description: String,
)