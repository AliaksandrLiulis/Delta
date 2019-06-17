package by.delta.service.impl

import by.delta.converter.impl.FaceConverter
import by.delta.dto.FaceDto
import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Face
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.specification.impl.face.GetFaceByFaceId
import by.delta.specification.impl.face.GetFaceByUserEmail
import by.delta.specification.impl.face.GetFaceByUserId
import by.delta.util.ConstParamService
import by.delta.util.Helper
import by.delta.validator.FaceValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils

@Service
open class FaceServiceImpl @Autowired constructor(private val faceRepository: IRepository<Face>,
                                                  private val faceConverter: FaceConverter,
                                                  private val faceValidator: FaceValidator)
    : IFaceService {

    @Transactional
    override fun createFace(faceDto: FaceDto): FaceDto {
        return faceConverter.modelToDto(faceRepository.save(faceConverter.dtoToModel(faceDto)))
    }

    override fun getFaceById(recipientId: Long): FaceDto {
        faceValidator.checkId(recipientId)
        return faceConverter.modelToDto(faceRepository.query(GetFaceByFaceId(Helper.getWraperId(recipientId)), 1, 0)[0])
    }

    override fun getFaceByUserId(recipientId: Long): FaceDto {
        val faces = faceRepository.query(GetFaceByUserId(Helper.getWraperId(recipientId)), 1, 0)
        if (CollectionUtils.isEmpty(faces)) {
            LOGGER.error("User with such id not exist")
            throw ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_ID_STRING)
        }
        return faceConverter.modelToDto(faces[0])
    }

    override fun getFaceByUserEmail(email: String): FaceDto {
        val faces = faceRepository.query(GetFaceByUserEmail(Helper.getWraperEmail(email)), 1, 0)
        if (CollectionUtils.isEmpty(faces)) {
            LOGGER.error("User with such e-mail not exist")
            throw ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_EMAIL_STRING)
        }
        return faceConverter.modelToDto(faces[0])
    }

    override fun getModelFaceByUserEmail(email: String): Face {
        val faces = faceRepository.query(GetFaceByUserEmail(Helper.getWraperEmail(email)), 1, 0)
        if (CollectionUtils.isEmpty(faces)) {
            LOGGER.error("User with such e-mail not exist")
            throw ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_EMAIL_STRING)
        }
        return faces[0]
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FaceServiceImpl::class.java)
    }
}