package com.imooc.flink.domain;

public class Access {

    public String device;
    public String deviceType;
    public String os;
    // startup启动；
    public String event;
    public String net;
    public String channel;
    public String uid;
    public int nu;  // 1新
    public int nu2;
    public String ip;  // ==> ip去解析
    public long time;
    public String version;
    public String province;
    public String city;

    public Product product;

    @Override
    public String toString() {
        return "Access{" +
                "device='" + device + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", os='" + os + '\'' +
                ", event='" + event + '\'' +
                ", net='" + net + '\'' +
                ", channel='" + channel + '\'' +
                ", uid='" + uid + '\'' +
                ", nu=" + nu +
                ", nu2=" + nu2 +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                ", version='" + version + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", product=" + product +
                '}';
    }
}
