package by.delta.service.impl

import by.delta.converter.impl.OrganizationConverter
import by.delta.dto.FaceDto
import by.delta.dto.OrganizationDto
import by.delta.model.Organization
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IOrganizationService
import by.delta.specification.impl.organization.GeoAllOrganizationByUNP
import by.delta.util.Helper
import by.delta.validator.OrganizationValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import javax.transaction.Transactional

@Service
open class OrganizationServiceImpl @Autowired constructor(private val organizationRepositoryImpl: IRepository<Organization>,
                                                          private val organizationConverter: OrganizationConverter,
                                                          private val faceServiceImpl: IFaceService,
                                                          private val organizationValidator: OrganizationValidator
) : IOrganizationService {

    @Transactional
    override fun createOrganization(organizationDto: OrganizationDto): OrganizationDto {
        organizationValidator.validate(organizationDto)
        val allOrg = organizationRepositoryImpl.query(GeoAllOrganizationByUNP(Helper.getWraperUNPOrganization(organizationDto.orgUNP)), 1, 0)
        if (!CollectionUtils.isEmpty(allOrg)) {
            return organizationConverter.modelToDto(allOrg[0])
        }
        val organization = organizationRepositoryImpl.save(organizationConverter.dtoToModel(organizationDto))
        var faceDto = FaceDto()
        faceDto.faceName = organization.orgIconNameDef
        faceDto.organizationDto = organizationConverter.modelToDto(organization)
        faceServiceImpl.createFace(faceDto)
        return organizationConverter.modelToDto(organization)
    }

    override fun getAllOrganization(allRequestParams: MutableMap<String, String>): Set<OrganizationDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl::class.java)
    }
}