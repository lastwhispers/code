package com.imooc.flink.domain;

public class EventCatagoryProductCount {

    public String event;
    public String catagory;
    public String product;
    public long count;
    public long start;
    public long end;

    public EventCatagoryProductCount() {
    }

    public EventCatagoryProductCount(String event, String catagory, String product, long count, long start, long end) {
        this.event = event;
        this.catagory = catagory;
        this.product = product;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
       return event + "\t" + catagory + "\t" + product + "\t" + count + "\t" + start + "\t" + end;
    }
}
