package by.delta.repository.impl

import by.delta.model.User
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import by.delta.specification.abstractspecification.ICountSpecification
import org.springframework.stereotype.Repository
import javax.persistence.criteria.Root
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery


@Repository
open class UserRepositoryImpl : AbstractRepository<User>() {

    override fun getById(id: Long): User {
        return entityManager.find(User::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<User>, limit: Int, offset: Int): List<User> {
        val cb = entityManager.criteriaBuilder
        val userCriteriaQuery = cb.createQuery(User::class.java)
        val root = userCriteriaQuery.from(User::class.java)
        return entityManager.createQuery(specification.toQuery(userCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }

    override fun countQuery(specification: ICountSpecification): Long {
        val cb = entityManager.criteriaBuilder
        val userCriteriaQuery = cb.createQuery()
        val root = userCriteriaQuery.from(User::class.java)
        val criteriaQuery = specification.toQuery(userCriteriaQuery, root, cb)
        return  entityManager.createQuery(criteriaQuery).singleResult.toString().toLong()
    }
}