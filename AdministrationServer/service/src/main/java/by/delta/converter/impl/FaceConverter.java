package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.FaceDto;
import by.delta.model.Face;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FaceConverter implements IConverter<Face, FaceDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Face dtoToModel(FaceDto faceDto) {
        Face face = new Face();
        modelMapper.map(faceDto, face);
        return face;
    }

    @Override
    public FaceDto modelToDto(Face face) {
        FaceDto faceDto = new FaceDto();
        modelMapper.map(face, faceDto);
        return faceDto;
    }

    @Override
    public Set<Face> dtoToModelList(Set<FaceDto> listDto) {
        Set<Face> listFace = new LinkedHashSet<>();
        for (FaceDto faceDto : listDto) {
            listFace.add(dtoToModel(faceDto));
        }
        return listFace;
    }

    @Override
    public Set<FaceDto> modelToDtoList(Set<Face> listModel) {
        Set<FaceDto> listFaceDto = new LinkedHashSet<>();
        for (Face face : listModel) {
            listFaceDto.add(modelToDto(face));
        }
        return listFaceDto;
    }
}