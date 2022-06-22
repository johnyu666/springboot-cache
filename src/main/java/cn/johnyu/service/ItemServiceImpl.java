package cn.johnyu.service;

import cn.johnyu.pojo.Item;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@CacheConfig(cacheNames = "myCache")//指定缓存区的名称
public class ItemServiceImpl implements ItemService{
    @Cacheable(key = "'items'")//指定返回的List在缓存区的key,key为SpEL
    @Override
    public List<Item> findAllItem() {
        System.out.println("被缓存的findAllItem方法，不会每次都执行....");
        List<Item> items=new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Item item=new Item(i);
            items.add(item);
        }
        return items;
    }

    @Cacheable(key="#id")//以方法参数名称做为key
    @Override
    public Item loadItem(int id) {
        System.out.println("被缓存的loadItem方法，id="+id);
        Item item=new Item(id);
        return item;
    }
    @CachePut(key ="#item.id" ) //每次调用此方法时，都会执行，但返回的Item会更新相应key=id的缓存
    @CacheEvict(key="'items'") //key=id刷新，会导致findAllItems失效，所以需要让其失效
//    以下是上述写法的组合
//    @Caching(cacheable = @Cacheable(key = "#item.id"),evict = @CacheEvict(key="'items'"))
    @Override
    public Item addItem(Item item) {
        System.out.println("每次都会执行的addItem");
        return item;
    }

}
