package by.delta.validator;

import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.model.Organization;
import by.delta.util.ConstParamService;
import org.springframework.stereotype.Component;

@Component
public class OrganizationValidator {

    public void validate(final Organization organizationDto) {
        CommonValidator.checkNullObject(organizationDto, "organization reference is null", ServiceErrorCode.REFERENCE_ORGANIZATION_IS_NULL, ConstParamService.ORGANIZATION_STRING);
        orgNameValidate(organizationDto);
        shortOrgNameValidate(organizationDto);
        orgIconNameDefValidate(organizationDto);
        orgUNPValidate(organizationDto);
    }

    private void orgNameValidate(final Organization organizationDto) {
        CommonValidator.checkNullStringParams(organizationDto.getOrgName(), "Organization name is null", ServiceErrorCode.NICK_NAME_USER_IS_NULL, ConstParamService.USER_NICK_NAME_STRING);
        CommonValidator.checkEmptyStringParams(organizationDto.getOrgName(), "Organization name is Empty", ServiceErrorCode.NICK_NAME_USER_IS_EMPTY, ConstParamService.USER_NICK_NAME_STRING);
        CommonValidator.checkLengthStringParams(organizationDto.getOrgName(), "Organization name is long so much", 30, ServiceErrorCode.NICK_NAME_USER_IS_LONG_SO_MUCH, ConstParamService.USER_NICK_NAME_STRING);
    }

    private void shortOrgNameValidate(final Organization organizationDto) {

    }

    private void orgIconNameDefValidate(final Organization organizationDto) {

    }

    private void orgUNPValidate(final Organization organizationDto) {

    }

}
