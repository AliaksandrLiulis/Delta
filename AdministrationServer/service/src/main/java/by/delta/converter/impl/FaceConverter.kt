package by.delta.converter.impl

import by.delta.converter.IConverter
import by.delta.dto.FaceDto
import by.delta.model.Face
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.LinkedHashSet

@Component
class FaceConverter @Autowired constructor(private val modelMapper: ModelMapper)
    : IConverter<Face, FaceDto> {

    override fun dtoToModel(faceDto: FaceDto): Face {
        val face = Face()
        modelMapper!!.map(faceDto, face)
        return face
    }

    override fun modelToDto(face: Face): FaceDto {
        val faceDto = FaceDto()
        modelMapper!!.map(face, faceDto)
        return faceDto
    }

    override fun dtoToModelList(listDto: Set<FaceDto>): Set<Face> {
        val listFace = LinkedHashSet<Face>()
        for (faceDto in listDto) {
            listFace.add(dtoToModel(faceDto))
        }
        return listFace
    }

    override fun modelToDtoList(listModel: Set<Face>): Set<FaceDto> {
        val listFaceDto = LinkedHashSet<FaceDto>()
        for (face in listModel) {
            listFaceDto.add(modelToDto(face))
        }
        return listFaceDto
    }
}