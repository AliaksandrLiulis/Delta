package by.delta.specification.impl.message;

import by.delta.model.Message;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;

import java.util.List;
import java.util.Map;

public class GetMessageById extends AbstractCriteriaQuerySpecification<Message> {

    public GetMessageById(Map<String, List<String>> params) {
        super(params);
    }
}
