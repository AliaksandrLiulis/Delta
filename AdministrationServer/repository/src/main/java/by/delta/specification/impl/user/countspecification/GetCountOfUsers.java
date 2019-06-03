package by.delta.specification.impl.user.countspecification;

import by.delta.model.User;
import by.delta.specification.abstractspecification.AbstractCountQuery;

import java.util.List;
import java.util.Map;

public class GetCountOfUsers extends AbstractCountQuery<User> {

    public GetCountOfUsers(Map<String, List<String>> params) {
        super(params);
    }
}