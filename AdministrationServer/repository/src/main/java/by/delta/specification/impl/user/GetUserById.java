package by.delta.specification.impl.user;

import by.delta.model.User;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;

import java.util.List;
import java.util.Map;

public class GetUserById extends AbstractCriteriaQuerySpecification<User> {

    public GetUserById(Map<String, List<String>> params) {
        super(params);
    }
}
