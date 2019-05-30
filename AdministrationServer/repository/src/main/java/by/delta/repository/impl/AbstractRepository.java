package by.delta.repository.impl;

import by.delta.model.abstractmodel.AbstractModel;
import by.delta.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<Entity extends AbstractModel> implements IRepository<Entity> {

    public AbstractRepository() {
    }

    @PersistenceContext
    protected EntityManager entityManager;

    public Entity save(Entity item) {
        return entityManager.merge(item);
    }

    public Entity update(Entity item) {
        return entityManager.merge(item);
    }

    public void remove(Long id) {
        entityManager.remove(getById(id));
    }

    abstract Entity getById(long id);
}