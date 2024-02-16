package com.epam.rd.qa.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Client implements Iterable<Deposit>{
    private Deposit[] deposits;

    public Client() {
        deposits = new Deposit[10];
    }

    public Client(Deposit[] deposits) {
        if (deposits == null || deposits.length == 0) {
            throw new IllegalArgumentException("Deposits array cannot be null");
        }
        this.deposits = new Deposit[10];
        //System.arraycopy(deposits, 0, this.deposits, 0, Math.min(deposits.length, this.deposits.length));
    }

    public boolean addDeposit(Deposit deposit){
        for (int i = 0; i < deposits.length ; i++) {
            if (deposits[i] == null){
                deposits[i] = deposit;
                return true;
            }
        }
        return false;
    }

    public BigDecimal totalIncome(){
        BigDecimal total = BigDecimal.ZERO;
        for (Deposit deposit : deposits) {
            if (deposit != null) {
                total = total.add(deposit.income());
            }
        }
        return total;
    }

    public BigDecimal maxIncome(){
        BigDecimal max = BigDecimal.ZERO;
        for (Deposit deposit : deposits){
            if (deposit != null){
                BigDecimal income = deposit.income();
                if (income.compareTo(max) > 0){
                    max = income;
                }
            }
        }
        return max.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal getIncomeByNumber(int number){
        if (number >= 0 && number < deposits.length && deposits[number] != null) {
            return deposits[number].income().setScale(2, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
    }

    @Override
    public Iterator<Deposit> iterator() {
        return new Iterator<Deposit>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                while (current < deposits.length && deposits[current] == null) {
                    current++;
                }
                return current < deposits.length;
            }

            @Override
            public Deposit next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more deposits");
                }
                return deposits[current++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }
        };
    }


    public void sortDeposits() {
        Arrays.sort(deposits, (o1, o2) -> {
            // Ambos depósitos son null, se consideran iguales.
            if (o1 == null && o2 == null) return 0;
            // Un depósito null siempre va después de uno no null.
            if (o1 == null) return 1;
            if (o2 == null) return -1;
            // Compara en orden descendente si ambos depósitos no son null.
            return o2.totalAmount().compareTo(o1.totalAmount());
        });
    }

    public int countPossibleToProlongDeposit() {
        int count = 0;
        for (Deposit deposit : deposits) {
            if (deposit instanceof Prolongable && ((Prolongable) deposit).canToProlong()) {
                count++;
            }
        }
        return count;
    }

}
