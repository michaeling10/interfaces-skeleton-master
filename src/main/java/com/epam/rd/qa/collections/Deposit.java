package com.epam.rd.qa.collections;
import java.math.BigDecimal;
public abstract class Deposit implements Comparable<Deposit>{
    protected final BigDecimal amount;
    protected final int period;

    protected Deposit(BigDecimal depositAmount, int depositPeriod) {
        if (depositPeriod <= 0 || depositAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Los numeros deben ser mayores a 0");
        }
        this.amount = depositAmount;
        this.period = depositPeriod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public abstract BigDecimal income();

    public BigDecimal totalAmount (){
        return amount.add(income());
    }

    @Override
    public int compareTo(Deposit other) {
        return this.totalAmount().compareTo(other.totalAmount());
    }

}
