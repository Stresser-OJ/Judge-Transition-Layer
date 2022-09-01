package com.chosanglyul.jtl.localfile

import com.chosanglyul.jtl.repository.File
import com.chosanglyul.jtl.repository.FileRaw
import com.chosanglyul.jtl.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Service
class LocalFileService(
    @Value("\${jtl.dirpath}") private val dirPathString: String,
    private val fileRepository: FileRepository,
) {
    // TODO Efficient Async File IO
    private val dirPath = Paths.get(dirPathString)

    fun newFile(fileRawMono: Mono<FileRaw>): Mono<Long> {
        return fileRawMono.map { file ->
            val uuid = UUID.randomUUID().toString()
            Files.write(dirPath.resolve(uuid), file.text.toByteArray())
            Pair(file, uuid)
        }.flatMap { info ->
            fileRepository.save(
                File(
                    uuid = info.second,
                    name = info.first.name,
                )
            )
        }.map { file ->
            file.id!!
        }
    }

    fun editFile(fileIdMono: Mono<Long>, fileMono: Mono<FileRaw>): Mono<Long> {
        val repoQuery = fileIdMono.flatMap { fileId ->
            fileRepository.findById(fileId)
        }
        return Mono.zip(repoQuery, fileMono).map { info ->
            Files.write(dirPath.resolve(info.t1.uuid), info.t2.text.toByteArray())
            File(
                id = info.t1.id,
                uuid = info.t1.uuid,
                name = info.t2.name,
            )
        }.flatMap { file ->
            fileRepository.save(file)
        }.map { file ->
            file.id!!
        }
    }

    fun loadFile(fileIdMono: Mono<Long>): Mono<FileRaw> {
        val repoQuery = fileIdMono.flatMap { fileId ->
            fileRepository.findById(fileId)
        }
        val streams = repoQuery.map { file ->
            Files.lines(dirPath.resolve(file.uuid))
        }.flatMap { stream ->
            Flux.fromStream(stream).collectList()
        }.map { list ->
            concat(list)
        }
        return Mono.zip(repoQuery, streams).map { info ->
            FileRaw(
                name = info.t1.name,
                text = info.t2,
            )
        }
    }

    fun concat(stringList: List<String>): String {
        var tmp = ""
        for (s in stringList)
            tmp += s + "\n"
        return tmp
    }
}
