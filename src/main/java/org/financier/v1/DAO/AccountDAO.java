package org.financier.v1.DAO;

import org.financier.v1.entity.Account;
import org.financier.v1.util.BaseDAO;

import java.util.Optional;

public class AccountDAO extends BaseDAO<Account> {
    public AccountDAO() {
        super(Account.class);
    }

    public Optional<Account> findByEmail(String email) {
        Account account = em.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.ofNullable(account);
    }
}
