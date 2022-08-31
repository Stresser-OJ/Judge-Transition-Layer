package com.chosanglyul.jtl.localfile

import com.chosanglyul.jtl.repository.FileRaw
import com.chosanglyul.jtl.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LocalFileService(
    @Value("\${jtl.dirpath}") val dirPath: String,
    val fileRepository: FileRepository,
) {
    fun saveFile(file: FileRaw) {}

    fun loadFile(fileId: Long): Mono<FileRaw> {
        return fileRepository.findById(fileId).map { file ->
            FileRaw(name = file.name, text = file.uuid)
        }
    }
}
