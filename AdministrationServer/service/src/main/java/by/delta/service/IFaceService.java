package by.delta.service;

import by.delta.dto.FaceDto;

public interface IFaceService {

    FaceDto createFace(FaceDto faceDto);
    FaceDto getFaceById(Long recipientId);

}
