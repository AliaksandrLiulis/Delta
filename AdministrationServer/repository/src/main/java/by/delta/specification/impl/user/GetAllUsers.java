package by.delta.specification.impl.user;

import by.delta.model.User;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllUsers extends AbstractCriteriaQuerySpecification<User> {

    public GetAllUsers(Map<String, List<String>> params) {
        super(params);
    }
}
