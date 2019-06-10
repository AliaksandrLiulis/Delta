package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto
import by.delta.model.Face
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class IncomingDto() : AbstractDto() {
    var topicMessage: Int? = null
    var messageState: Int? = null
    var faceDto: FaceDto? = null
    var messageDto: MessageDto? = null

    constructor(topicMessage: Int?,
                messageState: Int?,
                faceDto: FaceDto?,
                messageDto: MessageDto?) : this() {
        this.topicMessage = topicMessage
        this.messageState = messageState
        this.faceDto = faceDto
        this.messageDto = messageDto
    }
}