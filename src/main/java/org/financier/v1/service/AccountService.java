package org.financier.v1.service;

import org.financier.v1.dao.AccountDAO;
import org.financier.v1.entity.Account;
import org.financier.v1.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
@Stateless
public class AccountService {
    @Inject
    private AccountDAO accountDAO;

    public Account findByEmail(String email) throws ResourceNotFoundException {
        return accountDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email.Not.Found", "Email not found"));
    }
}
