package by.delta.repository.impl

import by.delta.model.User
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import org.springframework.stereotype.Repository

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@Repository
open class UserRepositoryImpl : AbstractRepository<User>() {

    internal override fun getById(id: Long): User {
        return entityManager.find(User::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<User>, limit: Int, offset: Int): List<User> {
        val cb = entityManager.criteriaBuilder
        val userCriteriaQuery = cb.createQuery(User::class.java)
        val root = userCriteriaQuery.from(User::class.java)
        return entityManager.createQuery(specification.toQuery(userCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }
}