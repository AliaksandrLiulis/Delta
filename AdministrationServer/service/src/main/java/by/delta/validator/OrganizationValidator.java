package by.delta.validator;

import by.delta.dto.OrganizationDto;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.model.Organization;
import by.delta.util.ConstParamService;
import org.springframework.stereotype.Component;

@Component
public class OrganizationValidator {

    public void validate(final OrganizationDto organizationDto) {
        CommonValidator.checkNullObject(organizationDto, "organization reference is null", ServiceErrorCode.REFERENCE_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_STRING);
        orgNameValidate(organizationDto);
        shortOrgNameValidate(organizationDto);
        orgIconNameDefValidate(organizationDto);
        orgUNPValidate(organizationDto);
    }

    private void orgNameValidate(final OrganizationDto organizationDto) {
        CommonValidator.checkNullStringParams(organizationDto.getOrgName(), "Organization name is null", ServiceErrorCode.NAME_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_NAME_STRING);
        CommonValidator.checkEmptyStringParams(organizationDto.getOrgName(), "Organization name is Empty", ServiceErrorCode.NAME_ORGANIZATION_IS_EMPTY, ConstParamService.ORGANIZATION_NAME_STRING);
        CommonValidator.checkLengthStringParams(organizationDto.getOrgName(), "Organization name is long so much", 100, ServiceErrorCode.NAME_ORGANIZATION_IS_LONG_SO_MUCH, ConstParamService.ORGANIZATION_NAME_STRING);
    }

    private void shortOrgNameValidate(final OrganizationDto organizationDto) {
        CommonValidator.checkNullStringParams(organizationDto.getShortOrgName(), "Organization short name is null", ServiceErrorCode.SHORT_NAME_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_SHORT_NAME_STRING);
        CommonValidator.checkEmptyStringParams(organizationDto.getShortOrgName(), "Organization short name is Empty", ServiceErrorCode.SHORT_NAME_ORGANIZATION_IS_EMPTY, ConstParamService.ORGANIZATION_SHORT_NAME_STRING);
        CommonValidator.checkLengthStringParams(organizationDto.getShortOrgName(), "Organization short name is long so much", 50, ServiceErrorCode.SHORT_NAME_ORGANIZATION_IS_LONG_SO_MUCH, ConstParamService.ORGANIZATION_SHORT_NAME_STRING);
    }

    private void orgIconNameDefValidate(final OrganizationDto organizationDto) {
        CommonValidator.checkNullStringParams(organizationDto.getOrgIconNameDef(), "Organization icon name is null", ServiceErrorCode.ICON_NAME_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_ICON_NAME_STRING);
        CommonValidator.checkEmptyStringParams(organizationDto.getOrgIconNameDef(), "Organization icon name is Empty", ServiceErrorCode.ICON_NAME_ORGANIZATION_IS_EMPTY, ConstParamService.ORGANIZATION_ICON_NAME_STRING);
        CommonValidator.checkLengthStringParams(organizationDto.getOrgIconNameDef(), "Organization icon name is long so much", 30, ServiceErrorCode.ICON_NAME_ORGANIZATION_IS_LONG_SO_MUCH, ConstParamService.ORGANIZATION_ICON_NAME_STRING);
    }


    private void orgUNPValidate(final OrganizationDto organizationDto) {
        CommonValidator.checkNullStringParams(organizationDto.getOrgUNP(), "Organization UNP is null", ServiceErrorCode.UNP_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_UNP_NAME_STRING);
        CommonValidator.checkEmptyStringParams(organizationDto.getOrgUNP(), "Organization UNP is Empty", ServiceErrorCode.UNP_ORGANIZATION_IS_EMPTY, ConstParamService.ORGANIZATION_UNP_NAME_STRING);
        CommonValidator.checkLengthStringParams(organizationDto.getOrgUNP(), "Organization UNP is long so much", 10, ServiceErrorCode.UNP_ORGANIZATION_IS_LONG_SO_MUCH, ConstParamService.ORGANIZATION_UNP_NAME_STRING);
    }
}
