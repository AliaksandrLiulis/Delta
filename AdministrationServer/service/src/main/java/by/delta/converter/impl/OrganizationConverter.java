package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.OrganizationDto;
import by.delta.model.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class OrganizationConverter implements IConverter<Organization, OrganizationDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Organization dtoToModel(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        modelMapper.map(organizationDto, organization);
        return organization;
    }

    @Override
    public OrganizationDto modelToDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        modelMapper.map(organization, organizationDto);
        return organizationDto;
    }

    @Override
    public Set<Organization> dtoToModelList(Set<OrganizationDto> listDto) {
        Set<Organization> listOrganization = new LinkedHashSet<>();
        for (OrganizationDto organizationDto : listDto) {
            listOrganization.add(dtoToModel(organizationDto));
        }
        return listOrganization;
    }

    @Override
    public Set<OrganizationDto> modelToDtoList(Set<Organization> listModel) {
        Set<OrganizationDto> listOrganizationDto = new LinkedHashSet<>();
        for (Organization organization : listModel) {
            listOrganizationDto.add(modelToDto(organization));
        }
        return listOrganizationDto;
    }
}