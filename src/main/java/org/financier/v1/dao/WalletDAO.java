package org.financier.v1.dao;

import org.financier.v1.entity.Wallet;
import org.financier.v1.util.BaseDAO;

public class WalletDAO extends BaseDAO<Wallet> {
    public WalletDAO(Class<Wallet> entityClass) {
        super(entityClass);
    }

    public boolean belongsToAccountId(Long id, Long accountId) {
        Wallet entity = em.getReference(entityClass, id);
        return entity != null && entity.getAccount().getId().equals(accountId);
    }

    public void softDelete(Wallet entity) {
        entity.setActive(false);
    }

    public void softDeleteById(Long id) {
        Wallet entity = em.getReference(entityClass, id);
        entity.setActive(false);
    }
}
