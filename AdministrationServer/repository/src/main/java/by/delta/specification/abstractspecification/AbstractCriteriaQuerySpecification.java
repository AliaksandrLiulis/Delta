package by.delta.specification.abstractspecification;
import by.delta.model.abstractmodel.AbstractModel;
import by.delta.specification.RepositoryConstParams;
import org.springframework.util.CollectionUtils;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AbstractCriteriaQuerySpecification<Entity extends AbstractModel> {

    protected Map<String, List<String>> params;

    public AbstractCriteriaQuerySpecification(Map<String, List<String>> params) {
        this.params = params;
    }

    public CriteriaQuery<Entity> toQuery(CriteriaQuery<Entity> query, final Root<Entity> root, final CriteriaBuilder criteriaBuilder) {
        Predicate predicate = getPredicate(query, root, criteriaBuilder);
        if (predicate != null) {
            query.where(predicate);
        }

        query = getOrderQuery(query, root, criteriaBuilder);
        return query;
    }

    protected CriteriaQuery<Entity> getOrderQuery(final CriteriaQuery<Entity> query, final Root<Entity> root, final CriteriaBuilder criteriaBuilder) {
        List<Order> orderList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            List<String> allSortParams = params.get(RepositoryConstParams.STRING_SORT);
            if (allSortParams != null) {
                List<String> list = allSortParams;
                list.forEach(s -> {
                    if (s.contains("-")) {
                        s = s.replaceAll("-", "");
                        orderList.add(criteriaBuilder.desc(root.get(s)));
                    } else {
                        orderList.add(criteriaBuilder.asc(root.get(s)));

                    }
                });
                return query.orderBy(orderList);
            }
        }
        return query.orderBy(criteriaBuilder.asc(root.get(RepositoryConstParams.ID_KEY)));
    }

    protected List<Predicate> getWhereCondition(final CriteriaQuery<Entity> query, final Root<Entity> root, final CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (!k.equals(RepositoryConstParams.STRING_SORT) && !k.equals(RepositoryConstParams.LIMIT_KEY) && !k.equals(RepositoryConstParams.OFFSET_KEY)) {
                    if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                        v.forEach(s -> {
                            conditionList.add(criteriaBuilder.equal(root.get(k), s));
                        });
                    }
                    if (k.equalsIgnoreCase(RepositoryConstParams.NAME_KEY)) {
                        v.forEach(s -> {
                            conditionList.add(criteriaBuilder.like(root.get(k), "%" + s + "%"));
                        });
                    }
                }
            });
        }
        return conditionList;
    }

    protected Predicate getPredicate(final CriteriaQuery<Entity> query, final Root<Entity> root, final CriteriaBuilder criteriaBuilder) {
        Predicate orClause = null;
        List<Predicate> conditionList = getWhereCondition(query, root, criteriaBuilder);
        if (!CollectionUtils.isEmpty(conditionList)) {
            if (conditionList.size() == 1) {
                orClause = conditionList.get(0);
            } else {
                orClause = criteriaBuilder.or(conditionList.toArray(new Predicate[conditionList.size()]));
            }
        }
        return orClause;
    }
}