package by.delta.converter.impl

import by.delta.converter.IConverter
import by.delta.dto.MessageDto
import by.delta.model.Message
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.LinkedHashSet

@Component
class MessageConverter @Autowired constructor(private val modelMapper: ModelMapper)
    : IConverter<Message, MessageDto> {

    override fun dtoToModel(messageDto: MessageDto): Message {
        val message = Message()
        modelMapper!!.map(messageDto, message)
        return message
    }

    override fun modelToDto(message: Message): MessageDto {
        val messageDto = MessageDto()
        modelMapper!!.map(message, messageDto)
        return messageDto
    }

    override fun dtoToModelList(listDto: Set<MessageDto>): Set<Message> {
        val listMessage = LinkedHashSet<Message>()
        for (messageDto in listDto) {
            listMessage.add(dtoToModel(messageDto))
        }
        return listMessage
    }

    override fun modelToDtoList(listModel: Set<Message>): Set<MessageDto> {
        val listMessageDto = LinkedHashSet<MessageDto>()
        for (message in listModel) {
            listMessageDto.add(modelToDto(message))
        }
        return listMessageDto
    }
}