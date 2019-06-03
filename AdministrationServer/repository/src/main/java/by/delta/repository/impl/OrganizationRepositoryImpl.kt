package by.delta.repository.impl

import by.delta.model.Organization
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import org.springframework.stereotype.Repository

@Repository
open class OrganizationRepositoryImpl : AbstractRepository<Organization>() {

    override fun getById(id: Long): Organization {
        return entityManager.find(Organization::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<Organization>, limit: Int, offset: Int): List<Organization> {
        val cb = entityManager.criteriaBuilder
        val faceCriteriaQuery = cb.createQuery(Organization::class.java)
        val root = faceCriteriaQuery.from(Organization::class.java)
        return entityManager.createQuery(specification.toQuery(faceCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }
}