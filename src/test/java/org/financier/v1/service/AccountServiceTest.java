package org.financier.v1.service;

import org.financier.v1.dao.AccountDAO;
import org.financier.v1.entity.Account;
import org.financier.v1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountDAO accountDAO;

    @Test
    void findByEmail_existEmail_returnAccount() throws ResourceNotFoundException {
        when(accountDAO.findByEmail("fake@email.com")).thenReturn(Optional.of(new Account()));
        Account account = accountService.findByEmail("fake@email.com");
        verify(accountDAO).findByEmail("fake@email.com");
    }

    @Test
    void findByEmail_notExistEmail_throwException() {
        assertThrows(ResourceNotFoundException.class, () -> {
            accountService.findByEmail("fake@email.com");
        });
    }
}