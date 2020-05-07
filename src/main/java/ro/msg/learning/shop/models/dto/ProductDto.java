package ro.msg.learning.shop.models.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private int pid;

    private int categoryId;

    private String name;

    private String description;

    private BigDecimal price;

    private double weight;

    private String imageUrl;

}
