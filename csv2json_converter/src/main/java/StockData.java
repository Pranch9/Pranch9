import com.fasterxml.jackson.databind.ObjectMapper;
import dto.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class StockData {

    private ArrayList<Purchase> purchases;
    private String pathFile;

    public StockData() {
        purchases = new ArrayList<Purchase>();
    }

    //читает csv файл и заполняет модельно для json
    public void loadDataFromFile() {
        try {
            Scanner scanner = new Scanner(new FileReader("src/main/resources/1.csv"));

            String line;
            Purchase purchase;
            PurchaseData purchaseData;
            ChequeItem chequeItem;
            Withdraws withdraws;
            Rewards rewards;

            long start = System.currentTimeMillis();
            // Пропускаем заголовки
            scanner.nextLine();
            line = scanner.nextLine();
            int counterLines = 0;

            while (scanner.hasNextLine()) {
                String[] results = line.split(";");
                String purchaseIdForLoop = results[11];

                double quantity = Double.parseDouble(results[5]);
                double sumItem = Double.parseDouble(results[6]);
                double sum = Double.parseDouble(results[9]);
                int userId = Integer.parseInt(results[0]);
                String description = results[15];
                double cashBack;
                double payment;
                if (results[7].isEmpty()) {
                    cashBack = 0;
                } else {
                    cashBack = Double.parseDouble(results[7]);
                }
                if (results[8].isEmpty()) {
                    payment = 0;
                } else {
                    payment = Double.parseDouble(results[8]);
                }


                //Создается покупку
                purchase = new Purchase();
                String uniqueID = UUID.randomUUID().toString();
                purchase.setDateTime(results[2] + "T" + results[3] + "Z");
                purchase.setId(uniqueID);
                purchase.setUserId(userId);
                purchase.setIdentity(results[1]);
                purchase.setDescription(results[13]);
                purchase.setLocationId(results[12]);
                purchase.setBrandId(results[14]);

                purchaseData = new PurchaseData();
                purchase.setPurchaseData(purchaseData);
                purchaseData.setAmount(sum);
                purchaseData.setExternalPurchaseId(results[11]);

                int counter = 1;

                //Формируем массив товаров, которые относятся к одному чеку
                ArrayList<ChequeItem> itemArrayList = new ArrayList<ChequeItem>();
                while (purchaseIdForLoop.equals(results[11]) && scanner.hasNextLine()) {
                    chequeItem = new ChequeItem();

                    if (purchaseIdForLoop.equals(results[11])) {
                        chequeItem.setAmount(sumItem);
                        chequeItem.setQuantity(quantity);
                        chequeItem.setPositionId(counter);
                        chequeItem.setDescription(description);
                        itemArrayList.add(chequeItem);
                        counter++;

                    } else {
                        counter = 1;
                    }
                    purchaseData.setChequeItems(itemArrayList);

                    //Записываем в новую переменную следующую строку и сравниваем ее с предыдущей
                    String line2 = scanner.nextLine();
                    String[] results2 = line2.split(";");
                    purchaseIdForLoop = results2[11];
                    quantity = Double.parseDouble(results2[5]);
                    sumItem = Double.parseDouble(results2[6]);
                    description = results2[15];
                    if (!purchaseIdForLoop.equals(results[11])) {
                        line = line2;
                    }
                }

                if (payment != 0) {
                    ArrayList<Withdraws> withdrawsArrayList = new ArrayList<Withdraws>();
                    withdraws = new Withdraws();
                    withdraws.setAmount(payment);
                 //   withdraws.setMoneyAmount(sum);
                    withdrawsArrayList.add(withdraws);

                    purchaseData.setWithdraws(withdrawsArrayList);
                }
                if (cashBack != 0) {
                    ArrayList<Rewards> rewardsArrayList = new ArrayList<Rewards>();
                    rewards = new Rewards();
                    rewards.setAmount(cashBack);
                    rewardsArrayList.add(rewards);

                    purchaseData.setRewards(rewardsArrayList);
                }

                purchases.add(purchase);
            }
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println(timeConsumedMillis);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private ArrayList<String> arrayList;

    //Создаем json-файл
    public void getJsonFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            arrayList = new ArrayList<>();
            for (int x = 0; x < purchases.size(); x++) {
                String jsonInString = objectMapper.writeValueAsString(purchases.get(x));
                arrayList.add(jsonInString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getConvertFile() throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/1.json");
        for (String line : arrayList) {
            writer.write(line + System.getProperty("line.separator"));
        }
        writer.close();
    }
}
