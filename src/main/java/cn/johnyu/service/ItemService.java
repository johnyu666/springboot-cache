package cn.johnyu.service;

import cn.johnyu.pojo.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAllItem();
    Item addItem(Item item);
    Item loadItem(int id);

}
