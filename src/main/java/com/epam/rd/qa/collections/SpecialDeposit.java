package com.epam.rd.qa.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit implements Prolongable{
    public SpecialDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        BigDecimal currentAmount = amount;
        BigDecimal interestRate;
        for (int i = 0; i < period; i++) {
            interestRate = new BigDecimal(i).divide(new BigDecimal("100"));
            currentAmount = currentAmount.add(currentAmount.multiply(interestRate));
        }

        return currentAmount.subtract(amount).setScale(2, RoundingMode.DOWN);
    }

    @Override
    public boolean canToProlong() {
        BigDecimal expectedDeposit = new BigDecimal("1000");
        boolean b = expectedDeposit.compareTo(amount) < 0;
        return b;
    }
}
