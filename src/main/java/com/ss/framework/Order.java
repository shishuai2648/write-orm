package com.ss.framework;

/**
 *      排序组件
 * @Author shishuai
 *  @email shishuai2648@163.com
 *   @Date 2019/4/27 9:56
 */
public class Order {

    private boolean ascending; // 升序还是降序
    private String propertyName; // 那个字段升序，那个字段降序

    public String toString(){
        return this.propertyName + ' ' + (ascending ? "asc":"desc");
    }

    public Order(String propertyName,boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    public static Order asc(String propertyName){
        return new Order(propertyName,true);
    }

    public static Order desc(String propertyName){
        return new Order(propertyName,false);
    }
}
