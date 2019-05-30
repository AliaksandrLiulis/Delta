package by.delta.repository.impl;

import by.delta.model.Face;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FaceRepositoryImpl extends AbstractRepository<Face> {

    @Override
    Face getById(long id) {
        return entityManager.find(Face.class, id);
    }

    @Override
    public List<Face> query(AbstractCriteriaQuerySpecification<Face> specification, int limit, int offset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Face> faceCriteriaQuery = cb.createQuery(Face.class);
        Root<Face> root = faceCriteriaQuery.from(Face.class);
        return entityManager.createQuery(specification.toQuery(faceCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).getResultList();
    }
}