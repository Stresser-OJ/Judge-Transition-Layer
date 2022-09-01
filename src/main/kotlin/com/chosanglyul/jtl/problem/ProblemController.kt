package com.chosanglyul.jtl.problem

import com.chosanglyul.jtl.repository.Problem
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProblemController(
    private val problemService: ProblemService
) {
    @PostMapping("/problems")
    fun makeProblem(
        @RequestBody @Validated makeProblemRequest: MakeProblemRequest,
    ): Mono<Long> {
        return problemService.makeProblem(makeProblemRequest)
    }

    @GetMapping("/problems")
    fun getProblem(): Flux<Problem> {
        return problemService.getAllProblems()
    }

    @GetMapping("/problems/{id}")
    fun getProblem(
        @PathVariable("id") @Validated problemId: Long,
    ): Mono<Problem> {
        return problemService.getProblemById(problemId)
    }
}
