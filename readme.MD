# 展示了Spring boot cache的使用情况
1. pom.xml中的两个依赖都是可选的,分别代表使用不同的CacheManager实现类

```xml
        <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

2. 当使用Redis服务器时，启动的docker脚本如下：

```shell
docker run --name some-redis -p 6379:6379 --rm -d redis
```
