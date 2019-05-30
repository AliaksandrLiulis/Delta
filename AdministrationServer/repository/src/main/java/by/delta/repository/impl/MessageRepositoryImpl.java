package by.delta.repository.impl;

import by.delta.model.Message;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MessageRepositoryImpl extends AbstractRepository<Message> {

    @Override
    Message getById(long id) {
        return entityManager.find(Message.class, id);
    }

    @Override
    public List<Message> query(AbstractCriteriaQuerySpecification<Message> specification, int limit, int offset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> messageCriteriaQuery = cb.createQuery(Message.class);
        Root<Message> root = messageCriteriaQuery.from(Message.class);
        return entityManager.createQuery(specification.toQuery(messageCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).getResultList();
    }
}
