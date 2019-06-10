package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AppealDto constructor(var appealState: Int? = null,
                            var messageDto: MessageDto? = null,
                            var incomingDto: IncomingDto? = null) : AbstractDto()


