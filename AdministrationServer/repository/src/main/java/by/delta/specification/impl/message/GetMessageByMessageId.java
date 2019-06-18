package by.delta.specification.impl.message;

import by.delta.model.Message;
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

public class GetMessageByMessageId extends AbstractCriteriaQuerySpecification<Message> {

    public GetMessageByMessageId(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Message> query, Root<Message> root, CriteriaBuilder criteriaBuilder) {
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