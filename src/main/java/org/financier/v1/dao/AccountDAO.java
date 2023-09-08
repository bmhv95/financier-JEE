package org.financier.v1.dao;

import org.financier.v1.entity.Account;
import org.financier.v1.util.BaseDAO;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class AccountDAO extends BaseDAO<Account> {
    public AccountDAO() {
        super(Account.class);
    }

    public Optional<Account> findByEmail(String email) {
        List<Account> account = em.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
                .setParameter("email", email)
                .getResultList();
        return account.isEmpty() ? Optional.empty() : Optional.of(account.get(0));
    }

    public void softDelete(Account entity) {
        entity.setStatus(false);
    }

    public void softDeleteById(Long id) {
        Account entity = em.getReference(entityClass, id);
        entity.setStatus(false);
    }
}
