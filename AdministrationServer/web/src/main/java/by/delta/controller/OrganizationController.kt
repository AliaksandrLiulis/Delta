package by.delta.controller

import by.delta.dto.OrganizationDto
import by.delta.dto.UserDto
import by.delta.model.Role
import by.delta.service.IOrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/organizations", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class OrganizationController @Autowired constructor(private val organizationService: IOrganizationService){

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrganization(@RequestBody resource: OrganizationDto): OrganizationDto {
        return organizationService.createOrganization(resource)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllOrganizations(@RequestParam allRequestParams: MutableMap <String, String>): Set<OrganizationDto> {
        return organizationService.getAllOrganization(allRequestParams)
    }
}