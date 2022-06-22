package cn.johnyu;

import cn.johnyu.pojo.Item;
import cn.johnyu.service.ItemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class CacheApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CacheApp.class);
        //根据依赖库的不同，会有三种表现形式：Spring自带，Ehcache实现，Redis实现等
        CacheManager manager = context.getBean(CacheManager.class);
        System.out.println(manager.getClass().getName());

        ItemService itemService = context.getBean(ItemService.class);
        //两次的查询调用，只有一次会实现执行业务
        List<Item> items= itemService.findAllItem();
        items= itemService.findAllItem();

        //调用addItem的副作用：(1)会把id=8的item加入到缓存中 (2)会让findAllItem的缓存失效
        Item item = new Item(8);
        itemService.addItem(item);

        itemService.loadItem(8);//会直接从缓存中查到数据

        itemService.findAllItem();//会实际调用业务方法，并重新生成缓存
    }
}
