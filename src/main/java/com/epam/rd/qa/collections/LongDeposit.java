package com.epam.rd.qa.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends Deposit implements Prolongable{
    public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        BigDecimal currentAmount = amount;
        if (period >= 7){
            BigDecimal interestRate = new BigDecimal("1.15");
            for (int i = 7; i <= period; i++) {
                currentAmount = currentAmount.multiply(interestRate);
            }
        }
        return period > 6 ? currentAmount.subtract(amount).setScale(2, RoundingMode.DOWN) : BigDecimal.ZERO.setScale(2,RoundingMode.DOWN);
    }

    @Override
    public boolean canToProlong() {
        return period < 36 ;
    }
}
