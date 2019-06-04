package by.delta.repository.impl

import by.delta.model.Incoming
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import by.delta.specification.abstractspecification.ICountSpecification
import org.springframework.stereotype.Repository

@Repository
open class IncomingRepository : AbstractRepository<Incoming>() {

    override fun getById(id: Long): Incoming {
        return entityManager.find(Incoming::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<Incoming>, limit: Int, offset: Int): List<Incoming> {
        val cb = entityManager.criteriaBuilder
        val incomingCriteriaQuery = cb.createQuery(Incoming::class.java)
        val root = incomingCriteriaQuery.from(Incoming::class.java)
        return entityManager.createQuery(specification.toQuery(incomingCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }

    override fun countQuery(specification: ICountSpecification): Long? {
        val cb = entityManager.criteriaBuilder
        val faceCriteriaQuery = cb.createQuery()
        val root = faceCriteriaQuery.from(Incoming::class.java)
        val criteriaQuery = specification.toQuery(faceCriteriaQuery, root, cb)
        return entityManager.createQuery(criteriaQuery).singleResult.toString().toLong()
    }
}