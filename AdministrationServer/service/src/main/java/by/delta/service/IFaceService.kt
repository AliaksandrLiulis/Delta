package by.delta.service

import by.delta.dto.FaceDto

open interface IFaceService {

    fun createFace(faceDto: FaceDto): FaceDto
    fun getFaceById(recipientId: Long): FaceDto
    fun getFaceByUserId(recipientId: Long): FaceDto
    fun getFaceByUserEmail(email: String): FaceDto


}