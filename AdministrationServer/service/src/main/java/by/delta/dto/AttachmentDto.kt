package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AttachmentDto constructor() : AbstractDto() {
    private var fileName: String? = null
    private var filePath: String? = null
    private var messageDto: MessageDto? = null

    constructor(fileName: String,
                filePath: String,
                messageDto: MessageDto) : this() {
        this.fileName = fileName
        this.filePath = filePath
        this.messageDto = messageDto
    }
}