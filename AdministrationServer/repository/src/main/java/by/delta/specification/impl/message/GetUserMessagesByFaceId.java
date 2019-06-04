package by.delta.specification.impl.message;

import by.delta.model.Message;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetUserMessagesByFaceId extends AbstractCriteriaQuerySpecification<Message> {

    public GetUserMessagesByFaceId(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Message> query, Root<Message> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
                params.forEach((k, v) -> {
                    if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                        v.forEach(s -> {
                            conditionList.add(criteriaBuilder.equal(root.get("face").get(k), s));
                        });
                    }
                });
        }
        return conditionList;
    }
    @Override
    protected CriteriaQuery<Message> getOrderQuery(final CriteriaQuery<Message> query, final Root<Message> root, final CriteriaBuilder criteriaBuilder) {
        List<Order> orderList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            List<String> allSortParams = params.get(RepositoryConstParams.STRING_SORT);
            if (allSortParams != null) {
                List<String> list = allSortParams;
                list.forEach(s -> {
                    if (s.contains("-")) {
                        s = s.replaceAll("-", "");
                        s = replaceParameters(s);
                        orderList.add(criteriaBuilder.desc(root.get(s)));
                    } else {
                        s = replaceParameters(s);
                        orderList.add(criteriaBuilder.asc(root.get(s)));

                    }
                });
                return query.orderBy(orderList);
            }
        }
        return query.orderBy(criteriaBuilder.asc(root.get(RepositoryConstParams.ID_KEY)));
    }

    private String replaceParameters(String s){

        if (s.equalsIgnoreCase("id")){
            return "id";
        }
        if (s.equalsIgnoreCase("messageSubject")){
            return "messageSubject";
        }
        if (s.equalsIgnoreCase("messageBody")){
            return "messageBody";
        }
        if (s.equalsIgnoreCase("createDate")){
            return "createDate";
        }
        if (s.equalsIgnoreCase("sendDate")){
            return "sendDate";
        }
        return "id";
    }


}
