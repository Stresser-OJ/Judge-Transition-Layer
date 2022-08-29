package com.chosanglyul.jtl.scoring

import com.chosanglyul.jtl.client.ClientSubmissionAddRequest
import com.chosanglyul.jtl.client.Judge0Client
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ScoringService(
    private val judge0Client: Judge0Client,
) {
    private val languageMap = mapOf<String, Long>("C++" to 50)

    fun addSubmission(addSubmissionRequest: SubmissionAddRequest): Mono<SubmissionAddResponse> {
        return judge0Client.addSubmission(ClientSubmissionAddRequest(
            source_code = addSubmissionRequest.source_code,
            language_id = languageMap[addSubmissionRequest.language]!!,
            stdin = addSubmissionRequest.stdin,
            expected_output = addSubmissionRequest.expected_output,
            cpu_time_limit = addSubmissionRequest.cpu_time_limit,
            memory_limit = addSubmissionRequest.memory_limit,
        ))
    }

    fun getSubmission(token: String): Mono<SubmissionRunResponse> {
        return judge0Client.getSubmission(token)
    }
}