package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AppealDto constructor(private val appealState: Int,
                            private val messageDto: MessageDto,
                            private val incomingDto: IncomingDto
) : AbstractDto()


