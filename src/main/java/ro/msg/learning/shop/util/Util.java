package ro.msg.learning.shop.util;

import ro.msg.learning.shop.models.entities.Stock;

public class Util {

    public static final String MAP_QUEST_URL = "http://www.mapquestapi.com/directions/v2/routematrix?key=iVbRG54dmavtKaaSZiF9ocmxzS6Nw8fA";

    public static int compareStocks(Stock stock1, Stock stock2) {
        return Integer.compare(stock1.getQuantity(), stock2.getQuantity());
    }

}
