package com.chosanglyul.jtl.client

import com.chosanglyul.jtl.scoring.SubmissionAddResponse
import com.chosanglyul.jtl.scoring.SubmissionRunResponse
import reactor.core.publisher.Mono

interface Client {
    fun addSubmission(source: ClientSubmissionAddRequest): Mono<SubmissionAddResponse>
    fun getSubmission(token: String): Mono<SubmissionRunResponse>
}
