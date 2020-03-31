package dto;

import java.util.ArrayList;
public class PurchaseData {
    private String externalPurchaseId;
    private double amount;
    private final String currencyExternalId = "718ae69b-76be-413f-ad19-7b7e02e4a438";
    private ArrayList<ChequeItem> chequeItems;
    private ArrayList<Withdraws> withdraws;
    private ArrayList<Rewards> rewards;

    public PurchaseData() {
    }

    public ArrayList<Withdraws> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(ArrayList<Withdraws> withdraws) {
        this.withdraws = withdraws;
    }

    public ArrayList<Rewards> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Rewards> rewards) {
        this.rewards = rewards;
    }

    public String getExternalPurchaseId() {
        return externalPurchaseId;
    }

    public void setExternalPurchaseId(String externalPurchaseId) {
        this.externalPurchaseId = externalPurchaseId;
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

    public ArrayList<ChequeItem> getChequeItems() {
        return chequeItems;
    }

    public void setChequeItems(ArrayList<ChequeItem> chequeItems) {
        this.chequeItems = chequeItems;
    }
}
