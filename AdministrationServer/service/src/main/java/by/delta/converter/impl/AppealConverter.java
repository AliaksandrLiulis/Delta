package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.AppealDto;
import by.delta.model.Appeal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class AppealConverter implements IConverter<Appeal, AppealDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Appeal dtoToModel(AppealDto appealDto) {
        Appeal appeal = new Appeal();
        modelMapper.map(appealDto, appeal);
        return appeal;
    }

    @Override
    public AppealDto modelToDto(Appeal appeal) {
        AppealDto appealDto = new AppealDto();
        modelMapper.map(appeal, appealDto);
        return appealDto;
    }

    @Override
    public Set<Appeal> dtoToModelList(Set<AppealDto> listDto) {
        Set<Appeal> listAppeal = new LinkedHashSet<>();
        for (AppealDto appealDto : listDto) {
            listAppeal.add(dtoToModel(appealDto));
        }
        return listAppeal;
    }

    @Override
    public Set<AppealDto> modelToDtoList(Set<Appeal> listModel) {
        Set<AppealDto> listAppealDto = new LinkedHashSet<>();
        for (Appeal appeal : listModel) {
            listAppealDto.add(modelToDto(appeal));
        }
        return listAppealDto;
    }
}