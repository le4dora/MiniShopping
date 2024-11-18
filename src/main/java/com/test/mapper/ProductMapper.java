package com.test.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.test.pojo.Product;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {

    @Select("select * from products where store_id=#{store_id}")
    List<Product> list(Integer store_id);

    @Insert("insert into products(product_name,store_id,description,product_pic,price,category,state,create_time,update_time)"+
            " values (#{product_name},#{store_id},#{description},#{product_pic},#{price},#{category},#{state},now(),now())")
    void add(Product product);


    @Select("select * from  products where id=#{id}")
    Product findById(Integer id);

    @Update("update products set product_name=#{product_name},description=#{description},product_pic=#{product_pic},price=#{price},category=#{category},update_time=#{update_time} where id=#{id}")
    void update(Product product);

    @Delete("delete from products where id=#{id}")
    void delete(Integer id);

    @Update("update products set state=#{state} where id=#{id}")
    void updateState(String state, Integer id);

    @SelectProvider(type = searchSql.class,method = "search")
    List<Product> search(String name, Integer store_id,Integer consumer_id,String description,Integer min_price,Integer max_price,String category, String state);


    @Update("update products set consumer_id=#{consumer_id},state='已出售' where id=#{id}")
    void buy(Integer id, Integer consumer_id);
}
