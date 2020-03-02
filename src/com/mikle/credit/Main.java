package com.mikle.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BigDecimal creditAmount = readValue(scanner, "credit amount");
        BigDecimal yearlyPercentage = readValue(scanner, "yearly percentage");
        BigDecimal monthlyPayment = readValue(scanner, "monthly payment");
        BigDecimal amountOfMonths = readValue(scanner, "amount of months");

        yearlyPercentage = yearlyPercentage.divide(BigDecimal.valueOf(100), 4, RoundingMode.CEILING);

        BigDecimal bankFeeSum = BigDecimal.ZERO;
        for (int i = 0; i < amountOfMonths.intValue(); i++) {
            BigDecimal bankFeeThisMonth = yearlyPercentage.multiply(BigDecimal.valueOf(30))
                    .multiply(creditAmount)
                    .divide(BigDecimal.valueOf(360), 10, RoundingMode.CEILING);
            BigDecimal actualPayment = monthlyPayment.subtract(bankFeeThisMonth);
            creditAmount = creditAmount.subtract(actualPayment);
            bankFeeSum = bankFeeSum.add(bankFeeThisMonth);
            System.out.printf("%s   |   %s\n", bankFeeThisMonth, creditAmount);
        }
        System.out.printf("Bank fee for the hole time: %s", bankFeeSum);
    }

    private static BigDecimal readValue(Scanner scanner, String valueName) {
        System.out.printf("Enter %s:   ", valueName);
        BigDecimal userInput = scanner.nextBigDecimal();
        System.out.printf("Entered %s: %s\n", valueName, userInput);
        return userInput;
    }

}
