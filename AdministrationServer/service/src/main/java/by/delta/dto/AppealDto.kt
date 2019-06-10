package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto

class AppealDto constructor() : AbstractDto() {
    var appealState: Int? = null
    var messageDto: MessageDto? = null
    var incomingDto: IncomingDto? = null

    constructor(appealState: Int,
                messageDto: MessageDto,
                incomingDto: IncomingDto) : this() {
        this.appealState = appealState
        this.messageDto = messageDto
        this.incomingDto = incomingDto
    }
}


