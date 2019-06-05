package by.delta.service.impl

import by.delta.converter.impl.IncomingConverter
import by.delta.dto.IncomingDto
import by.delta.model.Incoming
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IIncomingService
import by.delta.service.IMessageService
import by.delta.service.IUserService
import by.delta.validator.AuthenticationValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class IncomingServiceImpl @Autowired constructor(private val incomingRepository: IRepository<Incoming>,
                                                      private val incomingConverter: IncomingConverter,
                                                      private val authenticationValidator: AuthenticationValidator,
                                                      private val userService: IUserService,
                                                      private val messageService: IMessageService,
                                                      private val faceService: IFaceService
) : IIncomingService {

    @Transactional
    override fun createIncoming(authentication: Authentication?, resource: MutableMap<String, Array<Long>>): List<IncomingDto> {
        var list = ArrayList<IncomingDto>()
        authenticationValidator.validate(authentication)
        userService.checkAndGetUserByEmail(authentication!!.name)
        val recipients = resource["face_id"]
        val message = resource["message_id"]
        val existMessage = messageService.checkAndgetMessageById(message!![0])
        val iter = recipients!!.iterator()
        for (i in iter){
            var incomingDto = IncomingDto()
            val existUser = userService.getUserById(i)
            val faceExistUser = faceService.getFaceByUserId(existUser.id)
            incomingDto.messageState = 0
            incomingDto.messageDto = existMessage
            incomingDto.faceDto = faceExistUser
            var savedIncommingDto = incomingConverter.modelToDto(incomingRepository.save(incomingConverter.dtoToModel(incomingDto)))
            list.add(savedIncommingDto)
        }
        return list
    }
}