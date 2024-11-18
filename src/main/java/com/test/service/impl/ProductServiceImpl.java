package com.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.mapper.ProductMapper;
import com.test.pojo.PageBen;
import com.test.pojo.Product;
import com.test.service.ProductService;
import com.test.util.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void add(Product product) {
        //补充信息
        product.setState("待售");
        product.setCreate_time(LocalDateTime.now());
        product.setUpdate_time(LocalDateTime.now());
        //获取store_id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer store_id=(Integer) map.get("id");

        product.setStore_id(store_id);

        productMapper.add(product);
    }

    @Override
    public List<Product> list() {
        //获取id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer store_id=(Integer) map.get("id");
        System.out.println(store_id);
        return productMapper.list(store_id);
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public void update(Product product) {
        product.setUpdate_time(LocalDateTime.now());
        productMapper.update(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.delete(id);
    }

    @Override
    public void updateState(String state,Integer id) {
        productMapper.updateState(state,id);
    }

    @Override
public PageBen<Product> search(Integer pageNum, Integer pageSize, String name, Integer store_id,Integer consumer_id,String description,Integer min_price,Integer max_price,String category, String state){



        PageHelper.startPage(pageNum,pageSize);

        List<Product> as=productMapper.search( name, store_id, consumer_id,description, min_price, max_price,category, state);
        Page<Product> p=(Page<Product>)as;
        return new PageBen<>(p.getTotal(),p);
//        return new PageBen<>(p.getTotal(),productMapper.search( name, store_id, consumer_id,description, min_price, max_price,category, state));

    }

    @Override
    public void buy(Integer id,Integer consumer_id  ) {

        productMapper.buy(id,consumer_id);
    }

}
