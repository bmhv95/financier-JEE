package org.financier.v1.dao;

import org.financier.v1.entity.Spending;

public class SpendingDAO extends EnvelopeDAO<Spending> {
    protected SpendingDAO() {
        super(Spending.class);
    }
}
