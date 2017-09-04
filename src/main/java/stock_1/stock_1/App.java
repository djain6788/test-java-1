package stock_1.stock_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import stock_1.stock_1.objects.Data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class App 
{
    private static final String NIFTY_50_GAINERS = "https://www.nseindia.com/live_market/dynaContent/live_analysis/gainers/niftyGainers1.json";
    private static final String NIFTY_50_LOSERS = "https://www.nseindia.com/live_market/dynaContent/live_analysis/losers/niftyLosers1.json";
    private static final String NIFTY_NEXT_50_GAINERS = "https://www.nseindia.com/live_market/dynaContent/live_analysis/gainers/jrNiftyGainers1.json";
    private static final String NIFTY_NEXT_50_LOSERS = "https://www.nseindia.com/live_market/dynaContent/live_analysis/losers/jrNiftyLosers1.json";

    public static void main( String[] args ) throws IOException
    {
        URL nifty50Gainers = new URL(NIFTY_50_GAINERS);
        List<Data> dataNifty50Gainers = getStockData(nifty50Gainers);
        System.out.println("Nifty-50 Gainers: " + dataNifty50Gainers.toString());
        URL nifty50Losers = new URL(NIFTY_50_LOSERS);
        List<Data> dataNifty50Losers = getStockData(nifty50Losers);
        System.out.println("Nifty-50 Losers: " + dataNifty50Losers.toString());
        URL niftyNext50Gainers = new URL(NIFTY_NEXT_50_GAINERS);
        List<Data> dataNiftyNext50Gainers = getStockData(niftyNext50Gainers);
        System.out.println("Nifty-Next-50 Gainers: " + dataNiftyNext50Gainers.toString());
        URL niftyNext50Losers = new URL(NIFTY_NEXT_50_LOSERS);
        List<Data> dataNiftyNext50Losers = getStockData(niftyNext50Losers);
        System.out.println("Nifty-Next-50 Losers: " + dataNiftyNext50Losers.toString());
        //Alert alert = new Alert(AlertType.INFORMATION);
    }

    private static List<Data> getStockData(URL oracle) throws IOException {
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        Gson gson = new Gson();
        String inputLine;
        List<Data> stockList = new ArrayList<Data>();
        while ((inputLine = in.readLine()) != null) {
            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject)jsonParser.parse(inputLine);
            JsonElement data = jo.get("data");
            JsonArray stocksArray = data.getAsJsonArray();
            for (JsonElement element : stocksArray) {
                Data stock  = gson.fromJson(element, Data.class);
                //System.out.println("Data: " + stock.toString());
                stockList.add(stock);
            }
        }
        in.close();
        return stockList;
    }
}
