package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.AttachmentDto;
import by.delta.model.Attachment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class AttachmentConverter implements IConverter<Attachment, AttachmentDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Attachment dtoToModel(AttachmentDto attachmentDto) {
        Attachment attachement = new Attachment();
        modelMapper.map(attachmentDto, attachement);
        return attachement;
    }

    @Override
    public AttachmentDto modelToDto(Attachment attachement) {
        AttachmentDto attachmentDto = new AttachmentDto();
        modelMapper.map(attachement, attachmentDto);
        return attachmentDto;
    }

    @Override
    public Set<Attachment> dtoToModelList(Set<AttachmentDto> listDto) {
        Set<Attachment> listAttachementDto = new LinkedHashSet<>();
        for (AttachmentDto attachmentDto : listDto) {
            listAttachementDto.add(dtoToModel(attachmentDto));
        }
        return listAttachementDto;
    }

    @Override
    public Set<AttachmentDto> modelToDtoList(Set<Attachment> listModel) {
        Set<AttachmentDto> listAttachmentDto = new LinkedHashSet<>();
        for (Attachment attachment : listModel) {
            listAttachmentDto.add(modelToDto(attachment));
        }
        return listAttachmentDto;
    }
}