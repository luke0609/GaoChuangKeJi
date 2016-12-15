package com.jiaokaokeji.gaochuangkeji.book.prjo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Product {
    private int urls;
    private String title;


    public Product(int urls,String title) {
        this.urls = urls;
        this.title = title;
    }



    public int getUrls() {
        return urls;
    }

    public void setUrls(int urls) {
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "urls=" + urls +
                ", title='" + title + '\'' +
                '}';
    }
}
