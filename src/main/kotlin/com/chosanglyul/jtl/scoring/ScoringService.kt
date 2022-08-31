package com.chosanglyul.jtl.scoring

import com.chosanglyul.jtl.client.Client
import com.chosanglyul.jtl.client.ClientSubmissionAddRequest
import com.chosanglyul.jtl.client.Judge0Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ScoringService(
    @Value("\${jtl.judge.baseurl}") private val judgeURL: String,
) {
    private val languageMap = mapOf<String, Long>("C++" to 50)
    private val client: Client = Judge0Client(judgeURL)
    fun addSubmission(submissionAddRequest: SubmissionAddRequest): Mono<SubmissionAddResponse> {
        return addSubmission(
            ClientSubmissionAddRequest(
                source_code = submissionAddRequest.source_code,
                language_id = languageMap[submissionAddRequest.language]!!,
                stdin = submissionAddRequest.stdin,
                expected_output = submissionAddRequest.expected_output,
                cpu_time_limit = submissionAddRequest.cpu_time_limit,
                memory_limit = submissionAddRequest.memory_limit,
            )
        )
    }

    private fun addSubmission(clientSubmissionAddRequest: ClientSubmissionAddRequest): Mono<SubmissionAddResponse> {
        return client.addSubmission(clientSubmissionAddRequest)
    }

    fun getSubmission(token: String): Mono<SubmissionRunResponse> {
        return client.getSubmission(token)
    }
}
