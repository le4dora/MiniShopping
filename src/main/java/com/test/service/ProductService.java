package com.test.service;

import com.test.pojo.PageBen;
import com.test.pojo.Product;

import java.util.List;

public interface ProductService {


    //添加商品
    void add(Product product);

    //查询商家所有商品
    List<Product> list();

    //根据ID查询商品
    Product findById(Integer id);

    //更新商品信息
    void update(Product product);

    //删除商品
    void delete(Integer id);

    //更新商品状态
    void updateState(String state,Integer id);

    //列表搜索商品
    PageBen<Product> search(Integer pageNum, Integer pageSize, String name, Integer store_id,Integer consumer_id,String description,Integer min_price,Integer max_price,String category, String state);

    //用户购买商品
    void buy(Integer id,Integer consumer_id);

    //
}
