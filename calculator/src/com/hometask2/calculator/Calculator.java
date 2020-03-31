package com.hometask2.calculator;

public class Calculator {
    private double loanAmount;
    private double years;
    private double interestRate;
    private double extraPayment;
    private double sumSalary;
    private double familyMembers;
    private double yourAge;

    //Конструктор
    public Calculator(double loanAmount,
                      double years,
                      double interestRate,
                      double extraPayment,
                      double sumSalary,
                      double familyMembers,
                      double yourAge) {
        this.loanAmount = loanAmount;
        this.years = years;
        this.interestRate = interestRate;
        this.extraPayment = extraPayment;
        this.sumSalary = sumSalary;
        this.familyMembers = familyMembers;
        this.yourAge = yourAge;

        if (this.years <= 0 ||
                this.loanAmount <= 0 ||
                this.interestRate <= 0 ||
                this.extraPayment < 0 ||
                this.familyMembers <= 0 ||
                this.yourAge <= 0) {
            System.out.println("Значение должно быть неотрицательным");
            System.exit(0);
        } else if (this.sumSalary <= 0) {
            System.out.println("Чтобы получить кредить нужно еще и работать");
            System.exit(0);
        } else if (this.yourAge < 21) {
            System.out.println("Возвращайтесь, когда будет 21 год");
            System.exit(0);
        }
    }

    //Расчет ежемесячного платежа
    public double monthlyPayment() {
        double remainingDebt = loanAmount;
        double remainingMonth = years * 12;
        double monthPerсent = interestRate / 12 / 100;
        double monthlyPayment;
        monthlyPayment = (remainingDebt * monthPerсent) / (1 - Math.pow((1 + monthPerсent), (-1) * remainingMonth));
        return monthlyPayment;
    }

    //Вывод структуры объекта
    public String toString() {
        return "Calculator {" +
                "loanAmount= " + loanAmount +
                ", years= " + years +
                ", interestRate= " + interestRate +
                ", extraPayment= " + extraPayment +
                ", sumSalary= " + sumSalary +
                ", familyMembers= " + familyMembers +
                ", yourAge= " + yourAge +
                "}\n";
    }

    //Вывод решения о выдаче кредита
    public String printLoanNotApprovedMessage() {
        return "К сожалению, кредит Вам не одобрен";
    }

    //Расчет и формирование графика платежей
    public void payment() {
        double startingBalance, interest, principal, monthlyPayment, youPaid;
        double remainingDebt = loanAmount;
        double remainingMonth = years * 12;
        double monthPerсent = interestRate / 12 / 100;
        double principlePayment = extraPayment;
        double sum = 0;
        int monthID = 0;

        System.out.println("Месяц" + " | " +
                "Задолженность на начало месяца" + " | " +
                "Ваш платеж" + " | " +
                "Процент" + " | " +
                "Основной долг" + " | " +
                "Задолженность на конец месяца" + " | " +
                "Выплачено процентов" + "");
        monthlyPayment = (remainingDebt * monthPerсent) / (1 - Math.pow((1 + monthPerсent), (-1) * remainingMonth));

        while (remainingDebt > 0) {
            youPaid = monthlyPayment + principlePayment;
            startingBalance = remainingDebt;
            interest = remainingDebt * monthPerсent;
            principal = monthlyPayment + principlePayment - interest;
            remainingDebt = remainingDebt - principal;
            startingBalance = startingBalance + interest + principal - principlePayment - monthlyPayment;
            monthID++;
            sum = sum + interest;

            if (startingBalance >= monthlyPayment) {
                System.out.printf("%3d %21.2f %23.2f %9.2f %13.2f %22.2f %27.2f%n",
                        monthID,
                        startingBalance,
                        youPaid,
                        interest,
                        principal,
                        remainingDebt,
                        sum);
            } else if (startingBalance < monthlyPayment) {
                youPaid = startingBalance;
                principal = youPaid - interest;
                remainingDebt = startingBalance - youPaid;
                System.out.printf("%3d %21.2f %23.2f %9.2f %13.2f %22.2f %27.2f%n",
                        monthID,
                        startingBalance,
                        youPaid,
                        interest,
                        principal,
                        remainingDebt,
                        sum);
            }
        }
    }
}