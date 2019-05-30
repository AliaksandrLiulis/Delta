package by.delta.converter;

import java.util.Set;

public interface IConverter<Model, DTO> {

    Model dtoToModel(DTO dto);

    DTO modelToDto(Model model);

    Set<Model> dtoToModelList(Set<DTO> listDto);

    Set<DTO> modelToDtoList(Set<Model> listModel);
}
