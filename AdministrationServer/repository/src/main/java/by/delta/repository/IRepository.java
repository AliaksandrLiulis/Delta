package by.delta.repository;

import by.delta.model.abstractmodel.AbstractModel;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;

import java.util.List;

public interface IRepository<Entity extends AbstractModel> {

    Entity save(Entity item);

    Entity update(Entity item);

    void remove(Long id);

    List<Entity> query(AbstractCriteriaQuerySpecification<Entity> specification, int limit, int offset);
}