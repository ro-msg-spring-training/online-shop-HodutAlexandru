package ro.msg.learning.shop.models.dto;

import lombok.Data;

@Data
public class StockCsvDto {

    private int stockId;
    private String location;
    private String product;
    private int quantity;

}
