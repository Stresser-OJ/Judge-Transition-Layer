package com.chosanglyul.jtl.problem

import com.chosanglyul.jtl.localfile.LocalFileService
import com.chosanglyul.jtl.repository.FileRaw
import com.chosanglyul.jtl.repository.Problem
import com.chosanglyul.jtl.repository.ProblemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProblemService(
    private val problemRepository: ProblemRepository,
    private val localFileService: LocalFileService,
) {
    fun makeProblem(makeProblemRequest: MakeProblemRequest): Mono<Long> {
        return localFileService.newFile(
            FileRaw(
                name = makeProblemRequest.name + "_description",
                text = "",
            )
        ).map { descriptionId ->
            Problem(
                timeLimit = makeProblemRequest.timeLimit,
                memoryLimit = makeProblemRequest.memoryLimit,
                descriptionId = descriptionId,
            )
        }.flatMap { problem ->
            problemRepository.save(problem)
        }.map { problem ->
            problem.id!!
        }
    }

    fun getAllProblems(): Flux<Problem> {
        return problemRepository.findAll()
    }

    fun getProblemById(id: Long): Mono<Problem> {
        return problemRepository.findById(id)
    }
}
