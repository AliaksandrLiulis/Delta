package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.IncomingDto;
import by.delta.model.Incoming;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class IncomingConverter implements IConverter<Incoming, IncomingDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Incoming dtoToModel(IncomingDto incomingDto) {
        Incoming incoming = new Incoming();
        modelMapper.map(incomingDto, incoming);
        return incoming;
    }

    @Override
    public IncomingDto modelToDto(Incoming incoming) {
        IncomingDto incomingDto = new IncomingDto();
        modelMapper.map(incoming, incomingDto);
        return incomingDto;
    }

    @Override
    public Set<Incoming> dtoToModelList(Set<IncomingDto> listDto) {
        Set<Incoming> listIncoming = new LinkedHashSet<>();
        for (IncomingDto incomingDto : listDto) {
            listIncoming.add(dtoToModel(incomingDto));
        }
        return listIncoming;
    }

    @Override
    public Set<IncomingDto> modelToDtoList(Set<Incoming> listModel) {
        Set<IncomingDto> listIncomingDto = new LinkedHashSet<>();
        for (Incoming incoming : listModel) {
            listIncomingDto.add(modelToDto(incoming));
        }
        return listIncomingDto;
    }
}