package com.chosanglyul.jtl.scoring

import com.chosanglyul.jtl.client.ClientSubmissionAddRequest
import com.chosanglyul.jtl.client.Judge0Client
import com.chosanglyul.jtl.repository.Problem
import com.chosanglyul.jtl.repository.ProblemRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ScoringController(
    private val scoringService: ScoringService,
    private val problemRepository: ProblemRepository,
) {
    @PostMapping("/submission")
    fun addSubmission(
        @RequestBody @Validated addSubmissionRequest: SubmissionAddRequest,
    ): Mono<SubmissionAddResponse> {
        return scoringService.addSubmission(addSubmissionRequest)
    }

    @GetMapping("/submission")
    fun getSubmission(
        @RequestParam("token") token: String,
    ): Mono<SubmissionRunResponse> {
        return scoringService.getSubmission(token)
    }

    @GetMapping("/problems")
    fun getProblems(

    ): Flux<Problem> {
        return problemRepository.findAll()
    }
}