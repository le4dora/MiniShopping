package com.example.secondhand.service;


import com.example.secondhand.entity.Item;


import java.util.List;

public interface OrderService {

  void addItem(Item item);
  void removeItem(Item item);
  List<Item> searchItems();
  void updateItem(Item item);
  Item findItemByName(String name);



}
