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

    fun newFile(fileRaw: FileRaw): Mono<Long> {
        val uuid = UUID.randomUUID().toString()
        Files.write(dirPath.resolve(uuid), fileRaw.text.toByteArray())
        return fileRepository.save(
            File(
                uuid = uuid,
                name = fileRaw.name,
            )
        ).map { file ->
            file.id!!
        }
    }

    fun editFile(fileId: Long, fileRaw: FileRaw): Mono<Long> {
        return fileRepository.findById(fileId).map { file ->
            Files.write(dirPath.resolve(file.uuid), fileRaw.text.toByteArray())
            File(
                id = file.id,
                uuid = file.uuid,
                name = fileRaw.name,
            )
        }.flatMap { file ->
            fileRepository.save(file)
        }.map { file ->
            file.id!!
        }
    }

    fun loadFile(fileId: Long): Mono<FileRaw> {
        val repoQuery = fileRepository.findById(fileId)
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
