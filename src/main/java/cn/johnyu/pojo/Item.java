package cn.johnyu.pojo;

import java.io.Serializable;

public class Item implements Serializable {
    //此项必须加入，否则重启项目，反序列化时会出现异常
    private static final long serialVersionUID = 1L;
    private Integer id;

    public Item(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
