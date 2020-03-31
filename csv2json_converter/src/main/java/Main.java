import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StockData stockData = new StockData();
        //Читаем csv файла и заполняем модель "покупки"
        stockData.loadDataFromFile();
        //Формируем json-файл
        stockData.getJsonFile();
        stockData.getConvertFile();
    }
}
