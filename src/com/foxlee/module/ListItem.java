package com.foxlee.module;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ListItem {

    /** 总的listitem类型*/
    public ArrayList<String> items=new ArrayList<>();

    /**需要新建的listitem*/
    public ArrayList<String> undefaultitems=new ArrayList<>();

    /**listitem的前缀*/
    public String itemname;

    /**listitem的style名字*/
    public String stylename;
}
