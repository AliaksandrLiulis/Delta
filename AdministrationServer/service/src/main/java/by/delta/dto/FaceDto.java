package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaceDto extends AbstractDto {

    private UserDto userDto;

    private OrganizationDto organizationDto;

    private String faceName;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    @Override
    public String toString() {
        return "FaceDto{" +
                "userDto=" + userDto +
                ", organizationDto=" + organizationDto +
                ", faceName='" + faceName + '\'' +
                '}';
    }
}