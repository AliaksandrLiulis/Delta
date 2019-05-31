package by.delta.repository

import by.delta.model.abstractmodel.AbstractModel
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification

interface IRepository<Entity : AbstractModel> {

    fun save(item: Entity): Entity

    fun update(item: Entity): Entity

    fun remove(id: Long?)

    fun query(specification: AbstractCriteriaQuerySpecification<Entity>, limit: Int, offset: Int): List<Entity>
}