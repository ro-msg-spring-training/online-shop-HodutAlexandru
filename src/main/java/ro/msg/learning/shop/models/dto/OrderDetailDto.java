package ro.msg.learning.shop.models.dto;

import lombok.Data;


@Data
public class OrderDetailDto {

    private int id;

    private int oid;

    private int productId;

    private int quantity;

}
