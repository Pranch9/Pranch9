package com.hometask2.calculator;

import java.util.Scanner;

public class LoanAmortisation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Добро пожаловать в ипотечный калькулятор!");
        System.out.println("Введите через пробел Ваш возраст / ежемесячный доход / количество членов семьи");
        double yourAge = input.nextDouble();
        double sumSalary = input.nextDouble();
        double familyMembers = input.nextDouble();

        System.out.println("Введите через пробел сумму кредита / срок кредитования / процентную ставку / дополнительный ежемесячный платеж");

        double loanAmount = input.nextDouble();
        double years = input.nextDouble();
        double interestRate = input.nextDouble();
        double extraPayment = input.nextDouble();

        Calculator calculator = new Calculator(loanAmount, years, interestRate, extraPayment, sumSalary, familyMembers, yourAge);
        System.out.println(calculator.toString());
        yourAge = yourAge + years;

        if (yourAge > 70) {
            System.out.println("К сожалению, Вам не одобрен кредит");
            System.exit(0);
        }

        if (familyMembers == 1 && sumSalary < 0.5 * calculator.monthlyPayment()) {
        } else if (familyMembers == 2 && sumSalary * 0.45 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else if (familyMembers == 3 && sumSalary * 0.35 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else if (familyMembers == 4 && sumSalary * 0.30 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else if (familyMembers == 5 && sumSalary * 0.25 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else if (familyMembers > 5 && sumSalary * 0.05 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else if (familyMembers <= 0 && sumSalary * 0.6 < calculator.monthlyPayment()) {
            System.out.println(calculator.printLoanNotApprovedMessage());
        } else {
            calculator.payment();
        }
    }
}


