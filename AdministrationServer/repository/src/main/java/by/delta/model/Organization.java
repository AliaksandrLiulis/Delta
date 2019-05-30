package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "organizations")
public class Organization extends AbstractModel {

    @Column(name = "org_name", length = 100)
    private String orgName;
    @Column(name = "short_org_name", length = 50)
    private String shortOrgName;
    @Column(name = "org_icon_name_def", length = 30)
    private String orgIconNameDef;
    @Column(name = "org_unp", length = 10)
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(orgName, that.orgName)
                .append(shortOrgName, that.shortOrgName)
                .append(orgIconNameDef, that.orgIconNameDef)
                .append(orgUNP, that.orgUNP)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(orgName)
                .append(shortOrgName)
                .append(orgIconNameDef)
                .append(orgUNP)
                .toHashCode();
    }
}
