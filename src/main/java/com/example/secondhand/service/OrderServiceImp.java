package com.example.secondhand.service;


import com.example.secondhand.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  OrderServiceImp implements OrderService {


    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public List<Item> searchItems() {
        return List.of();
    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public Item findItemByName(String name) {
        return null;
    }
}
