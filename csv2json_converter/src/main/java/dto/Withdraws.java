package dto;

public class Withdraws {
    private double amount;
    private double moneyAmount;
    private final String currencyExternalId = "1f24174f-bfdb-4019-a3e7-4fb088b4a7a7";
    private final String withdrawType = "Bonus";
    private String description;

    public Withdraws() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getCurrencyExternalId() {
        return currencyExternalId;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public String getDescription() {
        return description;
    }
}
