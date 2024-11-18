package com.test.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    @NotNull(groups = {Update.class,UpdateState.class})
    private Integer id;//
    @NotEmpty(groups = {Add.class,Update.class})
    private String product_name;//
    private Integer store_id;//
    private Integer consumer_id;//
    private String description;//
    @NotEmpty(groups = {Add.class,Update.class})
    private String product_pic;//
    @NotNull(groups = {Add.class,Update.class})
    private float price;//
    @NotEmpty(groups = {Add.class,Update.class})
    private String category;//

    @NotEmpty(groups = {UpdateState.class})
    private String state;//

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_time;//修改时间


    public interface Add{ }

    public interface Update{ }

    public interface UpdateState{ }

}




//    public static Product copy(Product product) {
//        Product copy = new Product();
//        product.setProduct_name(product.getProduct_name());
//        product.setStore_id(product.getStore_id());
//        product.setDescription(product.getDescription());
//        product.setProduct_pic(product.getProduct_pic());
//        product.setPrice(product.getPrice());
//        product.setAmount(product.getAmount());
//        product.setCategory(product.getCategory());
//        product.setState(product.getState());
//        product.setCreate_time(product.getCreate_time());
//        product.setUpdate_time(product.getUpdate_time());
//        return copy;
//    }
