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
    fun makeProblem(makeProblemRequest: Mono<MakeProblemRequest>): Mono<Long> {
        val descriptionId = localFileService.newFile(
            makeProblemRequest.map { req ->
                FileRaw(
                    name = req.name + "_description",
                    text = "",
                )
            }
        )
        return Mono.zip(makeProblemRequest, descriptionId).map { info ->
            Problem(
                timeLimit = info.t1.timeLimit,
                memoryLimit = info.t1.memoryLimit,
                descriptionId = info.t2,
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

    fun getProblemById(idMono: Mono<Long>): Mono<Problem> {
        return idMono.flatMap { id ->
            problemRepository.findById(id)
        }
    }
}
