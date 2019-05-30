package by.delta.repository.impl;

import by.delta.model.User;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> {


    @Override
    User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> query(AbstractCriteriaQuerySpecification<User> specification, int limit, int offset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);
        return entityManager.createQuery(specification.toQuery(userCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).getResultList();
    }
}