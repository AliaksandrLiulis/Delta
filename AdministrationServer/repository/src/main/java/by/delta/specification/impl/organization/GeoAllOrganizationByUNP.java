package by.delta.specification.impl.organization;

import by.delta.model.Organization;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeoAllOrganizationByUNP extends AbstractCriteriaQuerySpecification<Organization> {

    public GeoAllOrganizationByUNP(Map<String, List<String>> params) {
        super(params);
    }

    protected List<Predicate> getWhereCondition(final CriteriaQuery<Organization> query, final Root<Organization> root, final CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (!k.equals(RepositoryConstParams.STRING_SORT) && !k.equals(RepositoryConstParams.LIMIT_KEY) && !k.equals(RepositoryConstParams.OFFSET_KEY)) {
                    if (k.equalsIgnoreCase(RepositoryConstParams.ORG_UNP_KEY)) {
                        v.forEach(s -> {
                            conditionList.add(criteriaBuilder.equal(root.get(RepositoryConstParams.ORG_UNP_KEY), s));
                        });
                    }
                }
            });
        }
        return conditionList;
    }

}
