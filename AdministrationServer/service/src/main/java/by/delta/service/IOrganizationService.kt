package by.delta.service

import by.delta.dto.OrganizationDto

interface IOrganizationService {

    fun createOrganization(organizationDto: OrganizationDto): OrganizationDto
    fun getAllOrganization(allRequestParams: MutableMap <String, String>):Set<OrganizationDto>
}