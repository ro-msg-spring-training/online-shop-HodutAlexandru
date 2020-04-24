package ro.msg.learning.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockCsv {

    private int stockId;
    private String location;
    private String product;
    private int quantity;

}
