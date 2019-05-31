package by.delta.ExtraClasses;

import by.delta.converter.impl.FaceConverter;
import by.delta.dto.FaceDto;
import by.delta.exception.ValidationException;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.model.Face;
import by.delta.repository.impl.FaceRepositoryImpl;
import by.delta.service.IFaceService;
import by.delta.specification.impl.face.GetFaceByFaceId;
import by.delta.specification.impl.face.GetFaceByUserEmail;
import by.delta.specification.impl.face.GetFaceByUserId;
import by.delta.util.ConstParamService;
import by.delta.util.Helper;
import by.delta.validator.FaceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

//@Service
public class FaceServiceImpl implements IFaceService {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(FaceServiceImpl.class);
//
//    @Autowired
//    private FaceRepositoryImpl faceRepository;
//    @Autowired
//    private FaceConverter faceConverter;
//    @Autowired
//    private FaceValidator faceValidator;
//
//    @Override
//    @Transactional
    public FaceDto createFace(FaceDto faceDto) {
//        return faceConverter.modelToDto(faceRepository.save(faceConverter.dtoToModel(faceDto)));
        return null;
    }

    @Override
    public FaceDto getFaceById(final Long recipientId) {
//        faceValidator.checkId(recipientId);
//        return faceConverter.modelToDto(faceRepository.query(new GetFaceByFaceId(Helper.getWraperId(recipientId)), 1, 0).get(0));
        return null;
    }

    public FaceDto getFaceByUserEmail(final String email) {
//        List<Face> faces = faceRepository.query(new GetFaceByUserEmail(Helper.getWraperEmail(email)), 1, 0);
//        if (CollectionUtils.isEmpty(faces)) {
//            LOGGER.error("User with such e-mail not exist");
//            throw new ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_EMAIL_STRING);
//        }
//        return faceConverter.modelToDto(faces.get(0));
        return null;
    }

    public FaceDto getFaceByUserId(final Long id) {
//        List<Face> faces = faceRepository.query(new GetFaceByUserId(Helper.getWraperId(id)), 1, 0);
//        if (CollectionUtils.isEmpty(faces)) {
//            LOGGER.error("User with such id not exist");
//            throw new ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_ID_STRING);
//        }
//        return faceConverter.modelToDto(faces.get(0));
        return null;
    }
}