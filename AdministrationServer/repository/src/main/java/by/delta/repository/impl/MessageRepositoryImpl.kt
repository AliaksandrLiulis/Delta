package by.delta.repository.impl

import by.delta.model.Message
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import org.springframework.stereotype.Repository

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@Repository
open class MessageRepositoryImpl : AbstractRepository<Message>() {

    internal override fun getById(id: Long): Message {
        return entityManager.find(Message::class.java, id)
    }

    override fun query(specification: AbstractCriteriaQuerySpecification<Message>, limit: Int, offset: Int): List<Message> {
        val cb = entityManager.criteriaBuilder
        val messageCriteriaQuery = cb.createQuery(Message::class.java)
        val root = messageCriteriaQuery.from(Message::class.java)
        return entityManager.createQuery(specification.toQuery(messageCriteriaQuery, root, cb)).setMaxResults(limit).setFirstResult(offset).resultList
    }
}
