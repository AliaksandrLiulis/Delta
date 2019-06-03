package by.delta.repository

import by.delta.model.abstractmodel.AbstractModel
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import by.delta.specification.abstractspecification.ICountSpecification



interface IRepository<Entity : AbstractModel> {

    fun save(item: Entity): Entity

    fun update(item: Entity): Entity

    fun remove(id: Long?)

    fun query(specification: AbstractCriteriaQuerySpecification<Entity>, limit: Int, offset: Int): List<Entity>

    fun countQuery(specification: ICountSpecification): Long?
}