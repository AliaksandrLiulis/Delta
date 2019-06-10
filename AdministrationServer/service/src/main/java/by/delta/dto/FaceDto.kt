package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class FaceDto(var userDto: UserDto? = null,
              var organizationDto: OrganizationDto? = null,
              var faceName: String? = null
) : AbstractDto()

