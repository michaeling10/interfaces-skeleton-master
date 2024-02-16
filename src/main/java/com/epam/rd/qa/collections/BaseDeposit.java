package com.epam.rd.qa.collections;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit{
    public BaseDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        BigDecimal currentAmount = amount;
        BigDecimal interestRate = new BigDecimal("1.05");
        for (int i = 0; i < period; i++) {
            currentAmount = currentAmount.multiply(interestRate);
        }
        return currentAmount.subtract(amount).setScale(2, RoundingMode.DOWN);
    }
}
