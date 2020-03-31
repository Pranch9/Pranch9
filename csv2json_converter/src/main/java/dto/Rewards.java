package dto;

public class Rewards {
    private double amount;
    private final String currencyExternalId = "1f24174f-bfdb-4019-a3e7-4fb088b4a7a7";
    private final String rewardType = "Bonus";
    private String description;

    public Rewards() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyExternalId() {
        return currencyExternalId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public String getDescription() {
        return description;
    }
}
