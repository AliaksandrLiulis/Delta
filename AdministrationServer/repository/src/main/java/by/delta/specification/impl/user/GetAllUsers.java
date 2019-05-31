package by.delta.specification.impl.user;

import by.delta.model.User;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;

import java.util.List;
import java.util.Map;

public class GetAllUsers extends AbstractCriteriaQuerySpecification<User> {

    public GetAllUsers(Map<String, List<String>> params) {
        super(params);
    }
}
