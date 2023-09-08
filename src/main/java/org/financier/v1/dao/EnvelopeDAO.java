package org.financier.v1.dao;

import org.financier.v1.entity.Envelope;
import org.financier.v1.util.BaseDAO;

public abstract class EnvelopeDAO<T extends Envelope> extends BaseDAO<T> {
    protected EnvelopeDAO(Class<T> entityClass) {
        super(entityClass);
    }

    public boolean belongsToAccountId(Long id, Long accountId) {
        T entity = em.getReference(entityClass, id);
        return entity != null && entity.getAccount().getId().equals(accountId);
    }

    public void softDelete(T entity) {
        entity.setActive(false);
    }

    public void softDeleteById(Long id) {
        T entity = em.getReference(entityClass, id);
        entity.setActive(false);
    }
}
