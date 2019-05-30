package by.delta.service.impl;

import by.delta.converter.impl.FaceConverter;
import by.delta.dto.FaceDto;
import by.delta.model.Face;
import by.delta.model.User;
import by.delta.repository.impl.FaceRepositoryImpl;
import by.delta.service.IFaceService;
import by.delta.specification.impl.face.GetFaceByFaceId;
import by.delta.util.Helper;
import by.delta.validator.FaceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FaceServiceImpl implements IFaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceServiceImpl.class);

    @Autowired
    private FaceRepositoryImpl faceRepository;
    @Autowired
    private FaceConverter faceConverter;
    @Autowired
    private FaceValidator faceValidator;

    @Override
    @Transactional
    public FaceDto createFace(FaceDto faceDto) {
        return faceConverter.modelToDto(faceRepository.save(faceConverter.dtoToModel(faceDto)));
    }

    @Override
    public FaceDto getFaceById(Long recipientId) {
        faceValidator.checkId(recipientId);
        Face face = faceRepository.query(new GetFaceByFaceId(Helper.getWraperId(recipientId)), 1, 0).get(0);
        User user = face.getUser();
        return faceConverter.modelToDto(face);
    }
}