package by.delta.repository.impl

import by.delta.model.Face
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import org.springframework.stereotype.Repository

@Repository
open class FaceRepositoryImpl : AbstractRepository<Face>() {

    internal override fun getById(id: Long): Face {
        return entityManager.find(Face::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<Face>, limit: Int, offset: Int): List<Face> {
        val cb = entityManager.criteriaBuilder
        val faceCriteriaQuery = cb.createQuery(Face::class.java)
        val root = faceCriteriaQuery.from(Face::class.java)
        return entityManager.createQuery(specification.toQuery(faceCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }
}