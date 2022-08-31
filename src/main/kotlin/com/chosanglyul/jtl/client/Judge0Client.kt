package com.chosanglyul.jtl.client

import com.chosanglyul.jtl.scoring.SubmissionAddResponse
import com.chosanglyul.jtl.scoring.SubmissionRunResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

class Judge0Client(
    private val baseurl: String,
) : Client {
    private val client: WebClient = WebClient.create(baseurl)

    override fun addSubmission(info: ClientSubmissionAddRequest): Mono<SubmissionAddResponse> {
        return client.post().uri("/submissions").bodyValue(info).retrieve().bodyToMono()
    }

    override fun getSubmission(token: String): Mono<SubmissionRunResponse> {
        return client.get().uri("/submissions/$token").retrieve().bodyToMono()
    }
}
