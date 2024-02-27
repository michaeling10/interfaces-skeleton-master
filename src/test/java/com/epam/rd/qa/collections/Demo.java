package com.epam.rd.qa.collections;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Deposit> deposits = new ArrayList<>();
        deposits.add(new SpecialDeposit(new BigDecimal("1000"), 12));
        deposits.add(new SpecialDeposit(new BigDecimal("2000"), 24));
        deposits.add(new SpecialDeposit(new BigDecimal("3000"), 6));

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal maxIncome = BigDecimal.ZERO;

        for (Deposit deposit : deposits) {
            BigDecimal income = deposit.income();
            System.out.println("Deposit income: " + income);

            totalIncome = totalIncome.add(income);
            if (income.compareTo(maxIncome) > 0) {
                maxIncome = income;
            }
        }

        System.out.println("Total income: " + totalIncome);
        System.out.println("Max income: " + maxIncome);
    }
}
