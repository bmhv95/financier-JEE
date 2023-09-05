package org.financier.v1.listener;


import org.financier.v1.entity.Income;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;

public class IncomeListener {
    private Income previousIncome;

    @PostLoad
    public void postLoadIncome(Income income) {
        this.previousIncome = Income.builder()
                .id(income.getId())
                .amount(income.getAmount())
                .wallet(income.getWallet())
                .build();
    }


    @PreRemove
    public void removeIncome(Income income) {
        subtractWalletMoney(previousIncome);
    }

    @PreUpdate
    public void updateIncome(Income income){
        if(income.getWallet() != null || income.getAmount() != null){
            subtractWalletMoney(previousIncome);
            updateIncomeWallet(income);
        }
    }

    @PostPersist
    public void persistIncome(Income income) {
        updateIncomeWallet(income);
    }

    private void subtractWalletMoney(Income income) {
        BigDecimal walletBalance = income.getWallet().getBalance();
        BigDecimal newBalance = walletBalance.subtract(income.getAmount());
        income.getWallet().setBalance(newBalance);
    }

    private void updateIncomeWallet(Income income) {
        BigDecimal walletBalance = income.getWallet().getBalance();
        BigDecimal newBalance = walletBalance.add(income.getAmount());
        income.getWallet().setBalance(newBalance);
    }
}
