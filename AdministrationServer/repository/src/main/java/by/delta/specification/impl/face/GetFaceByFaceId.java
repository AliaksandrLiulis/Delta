package by.delta.specification.impl.face;

import by.delta.model.Face;
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

public class GetFaceByFaceId extends AbstractCriteriaQuerySpecification<Face> {

    public GetFaceByFaceId(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Face> query, Root<Face> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.get(k), s));
                    });
                }
            });
        }
        return conditionList;
    }
}
