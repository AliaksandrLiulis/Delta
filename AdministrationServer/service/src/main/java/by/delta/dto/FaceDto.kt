package by.delta.dto

import by.delta.dto.abstractdto.AbstractDto
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class FaceDto(private val userDto: UserDto,
              private val organizationDto: OrganizationDto,
              private val faceName: String
) : AbstractDto() 