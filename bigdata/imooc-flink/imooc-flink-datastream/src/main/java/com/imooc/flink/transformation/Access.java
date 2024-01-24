package com.imooc.flink.transformation;

public class Access {
    private Long time;
    private String domain;
    private Double traffic;

    public Access() {
    }

    public Access(Long time, String domain, Double traffic) {
        this.time = time;
        this.domain = domain;
        this.traffic = traffic;
    }

    @Override
    public String toString() {
        return "Access{" +
                "time=" + time +
                ", domain='" + domain + '\'' +
                ", traffic=" + traffic +
                '}';
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Double getTraffic() {
        return traffic;
    }

    public void setTraffic(Double traffic) {
        this.traffic = traffic;
    }
}
