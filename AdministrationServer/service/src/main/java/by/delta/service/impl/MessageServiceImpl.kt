package by.delta.service.impl

import by.delta.converter.impl.MessageConverter
import by.delta.dto.MessageDto
import by.delta.model.Message
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IMessageService
import by.delta.specification.impl.message.GetUserMessagesByFaceId
import by.delta.util.ConstParamService
import by.delta.util.PagingParamsValidator
import by.delta.util.Helper
import by.delta.validator.MessageValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate


@Service
open class MessageServiceImpl @Autowired constructor(private val messageConverter: MessageConverter,
                                                     private val messageValidator: MessageValidator,
                                                     private val messageRepository: IRepository<Message>,
                                                     private val faceService: IFaceService)
    : IMessageService {

    @Transactional
    override fun createMessage(authentication: Authentication, messageDto: MessageDto): MessageDto {
        messageValidator.validate(messageDto)
        messageDto.createDate = LocalDate.now()
        messageDto.faceDto = faceService.getFaceByUserEmail(authentication.name)
        return messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)))
    }

    override fun getUserMessages(authentication: Authentication, allRequestParams: MutableMap<String, String>): Set<MessageDto> {
        val faceDto = faceService.getFaceByUserEmail(authentication.name)
        PagingParamsValidator.checkRequestParams(allRequestParams)
        val messages = messageRepository.query(GetUserMessagesByFaceId(Helper.getWraperId(faceDto.id)),
                allRequestParams.getValue(ConstParamService.LIMIT).toInt(), allRequestParams.getValue(ConstParamService.OFFSET).toInt())
        return messageConverter.modelToDtoList(messages.toSet())
    }
}