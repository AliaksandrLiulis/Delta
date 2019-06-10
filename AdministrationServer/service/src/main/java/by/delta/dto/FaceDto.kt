package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class FaceDto(
) : AbstractDto() {
    var userDto: UserDto? = null
    var organizationDto: OrganizationDto? = null
    var faceName: String? = null

    constructor(userDto: UserDto,
                organizationDto: OrganizationDto,
                faceName: String) : this() {
        this.userDto = userDto
        this.organizationDto = organizationDto
        this.faceName = faceName
    }

}

