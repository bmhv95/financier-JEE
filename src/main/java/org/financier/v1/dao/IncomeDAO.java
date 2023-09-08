package org.financier.v1.dao;

import org.financier.v1.entity.Income;
import org.financier.v1.util.BaseDAO;

public class IncomeDAO extends BaseDAO<Income> {
    public IncomeDAO() {
        super(Income.class);
    }
}
