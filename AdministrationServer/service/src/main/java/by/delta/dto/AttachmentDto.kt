package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AttachmentDto constructor(private val fileName: String,
                                private val filePath: String,
                                private val messageDto: MessageDto
) : AbstractDto()