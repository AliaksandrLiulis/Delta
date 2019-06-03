package by.delta.repository.impl

import by.delta.model.Message
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification
import by.delta.specification.abstractspecification.ICountSpecification
import org.springframework.stereotype.Repository

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

    override fun countQuery(specification: ICountSpecification): Long {
        val cb = entityManager.criteriaBuilder
        val messageCriteriaQuery = cb.createQuery()
        val root = messageCriteriaQuery.from(Message::class.java)
        val criteriaQuery = specification.toQuery(messageCriteriaQuery, root, cb)
        return  entityManager.createQuery(criteriaQuery).singleResult.toString().toLong()
    }
}
