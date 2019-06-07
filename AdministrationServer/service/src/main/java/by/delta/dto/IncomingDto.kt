package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto
import by.delta.model.Face
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class IncomingDto(private val topicMessage: Int,
                  private val messageState: Int,
                  private val faceDto: FaceDto,
                  private val messageDto: MessageDto
) : AbstractDto()