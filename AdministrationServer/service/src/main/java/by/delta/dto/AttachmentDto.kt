package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AttachmentDto constructor(var fileName: String? = null,
                                var filePath: String? = null,
                                var messageDto: MessageDto? = null
) : AbstractDto()