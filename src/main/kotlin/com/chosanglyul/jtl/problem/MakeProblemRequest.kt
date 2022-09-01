package com.chosanglyul.jtl.problem

import com.fasterxml.jackson.annotation.JsonProperty

class MakeProblemRequest(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("time_limit")
    val timeLimit: Float,

    @JsonProperty("memory_limit")
    val memoryLimit: Float,
)
