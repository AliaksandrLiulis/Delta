package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;

public class OrganizationDto extends AbstractDto {

    private String orgName;

    private String shortOrgName;

    private String orgIconNameDef;

    private String orgUNP;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShortOrgName() {
        return shortOrgName;
    }

    public void setShortOrgName(String shortOrgName) {
        this.shortOrgName = shortOrgName;
    }

    public String getOrgIconNameDef() {
        return orgIconNameDef;
    }

    public void setOrgIconNameDef(String orgIconNameDef) {
        this.orgIconNameDef = orgIconNameDef;
    }

    public String getOrgUNP() {
        return orgUNP;
    }

    public void setOrgUNP(String orgUNP) {
        this.orgUNP = orgUNP;
    }

    @Override
    public String toString() {
        return "OrganizationDto{" +
                "orgName='" + orgName + '\'' +
                ", shortOrgName='" + shortOrgName + '\'' +
                ", orgIconNameDef='" + orgIconNameDef + '\'' +
                ", orgUNP='" + orgUNP + '\'' +
                '}';
    }
}